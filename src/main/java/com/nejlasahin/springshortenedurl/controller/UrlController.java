package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/s/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable("shortUrl") String shortUrl){
        String originUrl = urlService.getOriginUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(originUrl)).build();
    }

    @PostMapping("user/{userId}/url/save")
    public ResponseEntity<UrlResponseDto> save(@RequestBody @Valid UrlRequestDto urlRequestDto, @PathVariable("userId") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.save(urlRequestDto, userId));
    }

    @GetMapping("user/{userId}/url/list")
    public ResponseEntity<List<UrlResponseDto>> getAll(@PathVariable("userId") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.getAll(userId));
    }

    @GetMapping("user/{userId}/url/detail/{urlId}")
    public ResponseEntity<UrlResponseDto> getById(@PathVariable("userId") Long userId, @PathVariable("urlId") Long urlId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.getById(userId, urlId));
    }

    @DeleteMapping("user/{userId}/url/detail/{urlId}")
    public ResponseEntity<?> deleteById(@PathVariable("userId") Long userId, @PathVariable("urlId") Long urlId){
        urlService.delete(userId, urlId);
        return ResponseEntity.status(HttpStatus.OK).body("Url is deleted.");
    }
}
