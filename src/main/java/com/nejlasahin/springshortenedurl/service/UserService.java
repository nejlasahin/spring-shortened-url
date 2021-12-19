package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.SignupDto;
import com.nejlasahin.springshortenedurl.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    SignupDto save(UserRequestDto userRequestDto);
    User update(User user, Long userId);
    void delete(Long userId);
    Optional<User> getByUsername(String username);
}