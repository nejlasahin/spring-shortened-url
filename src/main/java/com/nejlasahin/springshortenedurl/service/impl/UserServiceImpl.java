package com.nejlasahin.springshortenedurl.service.impl;

import com.nejlasahin.springshortenedurl.dto.converter.UserConverter;
import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.SignupDto;
import com.nejlasahin.springshortenedurl.exceptions.UserNotFoundException;
import com.nejlasahin.springshortenedurl.exceptions.UsernameIsAlreadyExistException;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignupDto save(UserRequestDto userRequestDto) {
        User user = UserConverter.userRequestDtoToUser(userRequestDto);
        userRepository.findByUsername(userRequestDto.getUsername())
                .ifPresent(s -> { throw new UsernameIsAlreadyExistException(String.format("Username with %s is already exist.", userRequestDto.getUsername())); } );
        User saveUser = userRepository.save(user);
        return new SignupDto(saveUser.getId());
    }

    @Override
    public User update(User user, Long userId) {
        return null;
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
