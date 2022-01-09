package com.nejlasahin.springshortenedurl.service.impl;

import com.google.common.hash.Hashing;
import com.nejlasahin.springshortenedurl.dto.converter.UrlConverter;
import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.exceptions.UrlNotFoundException;
import com.nejlasahin.springshortenedurl.exceptions.UserNotFoundException;
import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UrlRepository;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.UrlService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlServiceImpl(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UrlResponseDto save(UrlRequestDto urlRequestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        String shortUrl = encodeUrl(urlRequestDto.getOriginUrl());
        Url url = UrlConverter.urlRequestDtoToUrl(urlRequestDto);
        url.setShortUrl(shortUrl);
        url.setUser(user);
        urlRepository.save(url);
        return UrlConverter.urlToUrlResponseDto(url);
    }

    @Override
    public void delete(Long userId, Long urlId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        urlRepository.findByUserIdAndUrlId(userId, urlId)
                .orElseThrow(() -> new UrlNotFoundException(String.format("Url with id %d not found.", urlId)));
        urlRepository.deleteById(urlId);
    }

    @Override
    public List<UrlResponseDto> getAll(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        return UrlConverter.urlListToUrlResponseDtoList(urlRepository.findAllById(userId));
    }

    @Override
    public UrlResponseDto getById(Long userId, Long urlId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        Url url = urlRepository.findByUserIdAndUrlId(userId, urlId)
                .orElseThrow(() -> new UrlNotFoundException(String.format("Url with id %d not found.", urlId)));
        return UrlConverter.urlToUrlResponseDto(url);
    }

    @Override
    public String getOriginUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Url not found."));
        return url.getOriginUrl();
    }

    public String encodeUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.adler32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        urlRepository.findByShortUrl(encodedUrl)
                .ifPresent(resultUrl -> encodeUrl(resultUrl.getShortUrl().concat(time.toString())));
        return encodedUrl;
    }
}
