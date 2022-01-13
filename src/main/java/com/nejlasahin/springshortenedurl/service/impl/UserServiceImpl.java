package com.nejlasahin.springshortenedurl.service.impl;

import com.nejlasahin.springshortenedurl.dto.converter.UserConverter;
import com.nejlasahin.springshortenedurl.dto.request.UserPasswordDto;
import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.RegisterDto;
import com.nejlasahin.springshortenedurl.exceptions.UserNotFoundException;
import com.nejlasahin.springshortenedurl.exceptions.UsernameIsAlreadyExistException;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public RegisterDto save(UserRequestDto userRequestDto) {
        userRepository.findByUsername(userRequestDto.getUsername())
                .ifPresent(s -> {
                    throw new UsernameIsAlreadyExistException(String.format("Username with %s is already exist.", userRequestDto.getUsername()));
                });
        User user = userRepository.save(userConverter.userRequestDtoToUser(userRequestDto));
        return new RegisterDto(user.getId());
    }

    @Override
    public void changePassword(Long userId, UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        user.setPassword(new BCryptPasswordEncoder().encode(userPasswordDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found.", userId)));
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
