package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.model.Url;

import java.util.List;

public interface UrlService {

    Url save(Url url, Long userId);
    Url update(Url url, Long userId);
    void delete(Long userId, Long urlId);
    List<Url> getAll(Long userId);
    Url getById(Long userId, Long urlId);
}
