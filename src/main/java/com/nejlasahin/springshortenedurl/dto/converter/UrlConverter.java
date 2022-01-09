package com.nejlasahin.springshortenedurl.dto.converter;

import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.model.Url;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrlConverter {

    public static Url urlRequestDtoToUrl(UrlRequestDto urlRequestDto) {
        return Url.builder()
                .originUrl(urlRequestDto.getOriginUrl())
                .build();
    }

    public static UrlResponseDto urlToUrlResponseDto(Url url) {
        return UrlResponseDto.builder()
                .id(url.getId())
                .shortUrl(url.getShortUrl())
                .build();
    }

    public static List<UrlResponseDto> urlListToUrlResponseDtoList(List<Url> urlList) {
        return urlList.stream().map(UrlConverter::urlToUrlResponseDto).collect(Collectors.toList());
    }
}
