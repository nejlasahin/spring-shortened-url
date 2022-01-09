package com.nejlasahin.springshortenedurl.service;

import com.nejlasahin.springshortenedurl.dto.converter.UrlConverter;
import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.model.User;
import com.nejlasahin.springshortenedurl.repository.UrlRepository;
import com.nejlasahin.springshortenedurl.repository.UserRepository;
import com.nejlasahin.springshortenedurl.service.impl.UrlServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    UrlRepository urlRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UrlConverter urlConverter;

    @InjectMocks
    UrlServiceImpl urlServiceImpl;

    @Test
    public void save() {
        UrlRequestDto urlRequestDto = UrlRequestDto.builder().originUrl("www.test.com").build();
        Long userId = 1L;
        String shortUrl = "encode-test";
        User user = User.builder().id(userId).username("testname").password("test123").build();
        Url url = UrlConverter.urlRequestDtoToUrl(urlRequestDto);
        url.setShortUrl(shortUrl);
        url.setUser(user);
        UrlResponseDto urlResponseDto = UrlResponseDto.builder().id(10L).shortUrl(shortUrl).build();

        when(UrlConverter.urlRequestDtoToUrl(urlRequestDto)).thenReturn(url);
        when(urlRepository.save(url)).thenReturn(url);
        when(UrlConverter.urlToUrlResponseDto(url)).thenReturn(urlResponseDto);

        UrlResponseDto actual = this.urlServiceImpl.save(urlRequestDto, userId);


        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getShortUrl(), urlResponseDto.getShortUrl()),
                () -> assertEquals(actual, urlResponseDto),
                () -> assertEquals(actual.getId(), urlResponseDto.getId())
        );

    }

}
