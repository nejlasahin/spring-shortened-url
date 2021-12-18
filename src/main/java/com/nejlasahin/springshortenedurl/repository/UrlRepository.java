package com.nejlasahin.springshortenedurl.repository;

import com.nejlasahin.springshortenedurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
