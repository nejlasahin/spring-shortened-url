package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.model.Url;

import java.util.List;

public interface UrlService {

    Url save(Url url, Long userId);
    Url update(Url url, Long addressId);
    void delete(Long addressId);
    List<Url> getAll();
}
