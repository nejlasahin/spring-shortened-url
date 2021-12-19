package com.nejlasahin.springshortenedurl.dto.converter;

import com.nejlasahin.springshortenedurl.dto.request.UserRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.LoginDto;
import com.nejlasahin.springshortenedurl.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static User userRequestDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .build();
    }

    public static LoginDto userToUserResponseDto(User user) {
        return LoginDto.builder()
                .id(user.getId())
                .build();
    }

    public static List<LoginDto> urlListToUrlResponseDtoList(List<User> urlList) {
        return urlList.stream().map(UserConverter::userToUserResponseDto).collect(Collectors.toList());
    }
}
