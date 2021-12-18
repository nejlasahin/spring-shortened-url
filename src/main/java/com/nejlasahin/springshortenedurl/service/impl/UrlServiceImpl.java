package com.nejlasahin.springshortenedurl.service.impl;

import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.repository.UrlRepository;
import com.nejlasahin.springshortenedurl.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url save(Url url, Long userId) {
        return null;
    }

    @Override
    public Url update(Url url, Long addressId) {
        return null;
    }

    @Override
    public void delete(Long addressId) {

    }

    @Override
    public List<Url> getAll() {
        return null;
    }
}
