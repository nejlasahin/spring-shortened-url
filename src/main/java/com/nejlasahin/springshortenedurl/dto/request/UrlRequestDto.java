package com.nejlasahin.springshortenedurl.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlRequestDto {

    @NotBlank(message = "Url is mandatory.")
    private String originUrl;
}
