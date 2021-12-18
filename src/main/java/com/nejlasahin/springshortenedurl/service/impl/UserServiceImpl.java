package com.nejlasahin.springshortenedurl.service.impl;

import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, Long userId) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
