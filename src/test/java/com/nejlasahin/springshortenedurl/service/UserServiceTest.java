package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.dto.converter.UserConverter;
import com.nejlasahin.springshortenedurl.dto.request.UserPasswordDto;
import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.RegisterDto;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserConverter userConverter;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void save() {
        User user = User.builder().id(1L).username("testuser").password("test123").build();

        UserRequestDto userRequestDto = UserRequestDto.builder().username("testuser").password("test123").build();

        when(userRepository.findByUsername(userRequestDto.getUsername())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(userConverter.userRequestDtoToUser(userRequestDto))).thenReturn(user);

        RegisterDto actual = this.userServiceImpl.save(userRequestDto);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getId(), user.getId())
        );
    }

    @Test
    public void changePassword() {
        Long userId = 1L;
        UserPasswordDto userPasswordDto = UserPasswordDto.builder().password("test123").build();
        User user = User.builder().id(userId).username("testuser").password("test123").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertDoesNotThrow(
                () -> this.userServiceImpl.changePassword(userId, userPasswordDto)
        );
    }

    @Test
    public void delete() {
        Long userId = 1L;
        User user = User.builder().id(userId).username("testuser").password("test123").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertDoesNotThrow(
                () -> this.userServiceImpl.delete(userId)
        );
    }

    @Test
    public void getByUsername() {
        Long userId = 1L;
        String username = "testuser";
        User user = User.builder().id(userId).username(username).password("test123").build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> actual = this.userServiceImpl.getByUsername(username);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.get().getId(), user.getId()),
                () -> assertEquals(actual.get().getUsername(), user.getUsername())
        );
    }
}
