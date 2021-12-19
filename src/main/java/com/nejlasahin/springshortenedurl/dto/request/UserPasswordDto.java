package com.nejlasahin.springshortenedurl.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDto {

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 8, message = "Password must be min 8 characters.")
    private String password;
}
