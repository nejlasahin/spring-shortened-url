package com.nejlasahin.springshortenedurl.exceptions;

public class UrlNotFoundException extends RuntimeException{

    public UrlNotFoundException(String message) {
        super(message);
    }
}
