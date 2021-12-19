package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.SignupDto;
import com.nejlasahin.springshortenedurl.dto.response.UserResponseDto;
import com.nejlasahin.springshortenedurl.exceptions.UserNotFoundException;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.security.JwtTokenProvider;
import com.nejlasahin.springshortenedurl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<SignupDto> register(@RequestBody @Valid UserRequestDto userRequestDto){
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userService.getByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found.", userRequestDto.getUsername())));
        long id = user.getId();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(), userRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = "Bearer " + jwtTokenProvider.generateJwtToken(authentication);
        UserResponseDto userResponseDto = new UserResponseDto(id, jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
