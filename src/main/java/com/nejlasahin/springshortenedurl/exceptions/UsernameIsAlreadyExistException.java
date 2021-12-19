package com.nejlasahin.springshortenedurl.exceptions;

public class UsernameIsAlreadyExistException extends RuntimeException {

    public UsernameIsAlreadyExistException(String message) {
        super(message);
    }
}
