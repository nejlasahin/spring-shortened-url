package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;

import java.util.List;

public interface UrlService {

    UrlResponseDto save(UrlRequestDto urlRequestDto, Long userId);

    void delete(Long userId, Long urlId);

    List<UrlResponseDto> getAll(Long userId);

    UrlResponseDto getById(Long userId, Long urlId);

    String getOriginUrl(String shortUrl);
}
