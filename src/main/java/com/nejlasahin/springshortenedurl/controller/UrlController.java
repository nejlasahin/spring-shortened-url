package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.model.Url;
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

import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable("shortUrl") String shortUrl){
        return ResponseEntity.status(HttpStatus.OK).body("dd");
    }

    @PostMapping("user/{userId}/url/save")
    public ResponseEntity<Url> save(@RequestBody Url url, @PathVariable("userId") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.save(url, userId));
    }

    @GetMapping("user/{userId}/url/list")
    public ResponseEntity<List<Url>> getAll(@PathVariable("userId") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.getAll(userId));
    }

    @GetMapping("user/{userId}/url/detail/{urlId}")
    public ResponseEntity<Url> getById(@PathVariable("userId") Long userId, @PathVariable("urlId") Long urlId){
        return ResponseEntity.status(HttpStatus.OK).body(urlService.getById(userId, urlId));
    }

    @DeleteMapping("user/{userId}/url/detail/{urlId}")
    public ResponseEntity<?> deleteById(@PathVariable("userId") Long userId, @PathVariable("urlId") Long urlId){
        urlService.delete(userId, urlId);
        return ResponseEntity.status(HttpStatus.OK).body("Url is deleted.");
    }
}
