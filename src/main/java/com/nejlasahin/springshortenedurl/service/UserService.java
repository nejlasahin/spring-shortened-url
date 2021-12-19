package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.dto.request.UserPasswordDto;
import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.RegisterDto;
import com.nejlasahin.springshortenedurl.model.User;

import java.util.Optional;

public interface UserService {

    RegisterDto save(UserRequestDto userRequestDto);
    void changePassword(Long userId, UserPasswordDto userPasswordDto);
    void delete(Long userId);
    Optional<User> getByUsername(String username);
}