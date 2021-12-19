package com.nejlasahin.springshortenedurl.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "Url is mandatory.")
    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters.")
    private String username;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 8, message = "Password must be min 8 characters.")
    private String password;
}
