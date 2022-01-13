package com.nejlasahin.springshortenedurl.dto.converter;

import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.model.Url;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrlConverter {

    public Url urlRequestDtoToUrl(UrlRequestDto urlRequestDto) {
        return Url.builder()
                .originUrl(urlRequestDto.getOriginUrl())
                .build();
    }

    public UrlResponseDto urlToUrlResponseDto(Url url) {
        return UrlResponseDto.builder()
                .id(url.getId())
                .shortUrl(url.getShortUrl())
                .build();
    }

    public List<UrlResponseDto> urlListToUrlResponseDtoList(List<Url> urlList) {
        return urlList.stream().map(url -> urlToUrlResponseDto(url)).collect(Collectors.toList());
    }
}
