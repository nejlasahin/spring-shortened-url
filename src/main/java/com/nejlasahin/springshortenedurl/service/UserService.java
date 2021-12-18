package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user, Long userId);
    void delete(Long userId);
    List<User> getAll();

}