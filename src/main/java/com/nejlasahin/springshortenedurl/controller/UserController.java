package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
}
