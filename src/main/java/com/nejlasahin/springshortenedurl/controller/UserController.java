package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.dto.request.UserPasswordDto;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") Long userId){
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User is deleted.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable("userId") Long userId, @RequestBody @Valid UserPasswordDto userPasswordDto){
        userService.changePassword(userId, userPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully.");
    }

}
