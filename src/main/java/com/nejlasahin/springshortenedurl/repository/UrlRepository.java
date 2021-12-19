package com.nejlasahin.springshortenedurl.repository;

import com.nejlasahin.springshortenedurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query(value = "SELECT u FROM Url u WHERE u.user.id = :userId")
    List<Url> findAllById(Long userId);

    @Query(value = "SELECT u FROM Url u WHERE u.id = :urlId AND u.user.id = :userId")
    Optional<Url> findByUserIdAndUrlId(Long userId, Long urlId);

    Optional<Url> findByShortUrl(String shortUrl);
}
