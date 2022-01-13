package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.dto.request.UserPasswordDto;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void delete(){
        Long userId = 1L;

        ResponseEntity<?> actual = userController.delete(userId);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals("User is deleted.", actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }

    @Test
    public void changePassword(){
        Long userId = 1L;
        UserPasswordDto userPasswordDto = UserPasswordDto.builder().password("testuser").build();

        ResponseEntity<?> actual = userController.changePassword(userId, userPasswordDto);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals("Password changed successfully.", actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }
}
