package com.nejlasahin.springshortenedurl.dto.converter;

import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User userRequestDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .build();
    }
}
