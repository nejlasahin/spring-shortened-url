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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        Long userId = 1L;
        UrlRequestDto urlRequestDto = UrlRequestDto.builder().originUrl("www.test.com").build();
        User user = User.builder().id(1L).username("testname").password("test123").build();
        Url url = Url.builder().id(1l).originUrl("www.test.com").shortUrl("test").user(user).build();
        UrlResponseDto urlResponseDto = UrlResponseDto.builder().id(1L).shortUrl("test").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(urlConverter.urlRequestDtoToUrl(urlRequestDto)).thenReturn(url);
        when(urlRepository.save(url)).thenReturn(url);
        when(urlConverter.urlToUrlResponseDto(url)).thenReturn(urlResponseDto);

        UrlResponseDto actual = this.urlServiceImpl.save(urlRequestDto, userId);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getShortUrl(), urlResponseDto.getShortUrl()),
                () -> assertEquals(actual, urlResponseDto),
                () -> assertEquals(actual.getId(), urlResponseDto.getId())
        );
    }

    @Test
    public void delete(){
        Long userId = 1L;
        User user = User.builder().id(userId).username("testname").password("test123").build();

        Long urlId = 10L;
        Url url = Url.builder().id(urlId).originUrl("www.test.com").shortUrl("test").user(user).build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(urlRepository.findByUserIdAndUrlId(userId, urlId)).thenReturn(Optional.of(url));

        assertDoesNotThrow(
                () -> this.urlServiceImpl.delete(userId, urlId)
        );
    }

    @Test
    public void getAll(){
        Long userId = 1L;
        User user = User.builder().id(userId).username("testname").password("test123").build();

        UrlResponseDto urlResponseDto1 = UrlResponseDto.builder().id(1L).shortUrl("test1").build();
        UrlResponseDto urlResponseDto2 = UrlResponseDto.builder().id(2L).shortUrl("test2").build();
        UrlResponseDto urlResponseDto3 = UrlResponseDto.builder().id(3L).shortUrl("test3").build();

        List<UrlResponseDto> urlResponseDtoList = new ArrayList<>();
        urlResponseDtoList.add(urlResponseDto1);
        urlResponseDtoList.add(urlResponseDto2);
        urlResponseDtoList.add(urlResponseDto3);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(urlConverter.urlListToUrlResponseDtoList(urlRepository.findAllById(userId))).thenReturn(urlResponseDtoList);

        List<UrlResponseDto> urlResponseDtos = this.urlServiceImpl.getAll(userId);

        assertAll(
                () -> assertNotNull(urlResponseDtoList),
                () -> assertEquals(urlResponseDtos, urlResponseDtoList),
                () -> assertEquals(urlResponseDtos.size(), urlResponseDtoList.size())
        );

    }

    @Test
    public void getById(){
        Long userId = 1L;
        User user = User.builder().id(userId).username("testname").password("test123").build();

        Long urlId = 10L;
        Url url = Url.builder().id(urlId).originUrl("www.test.com").shortUrl("test").user(user).build();

        UrlResponseDto urlResponseDto = UrlResponseDto.builder().id(1L).shortUrl("test").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(urlRepository.findByUserIdAndUrlId(userId, urlId)).thenReturn(Optional.of(url));
        when(urlConverter.urlToUrlResponseDto(url)).thenReturn(urlResponseDto);

        UrlResponseDto actual = this.urlServiceImpl.getById(userId, urlId);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, urlResponseDto),
                () -> assertEquals(actual.getShortUrl(), urlResponseDto.getShortUrl())
        );

    }

    @Test
    public void getOriginUrl(){
        Long userId = 1L;
        User user = User.builder().id(userId).username("testname").password("test123").build();

        Long urlId = 10L;
        Url url = Url.builder().id(urlId).originUrl("www.test.com").shortUrl("test").user(user).build();

        String shortUrl="test";

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(url));

        String actual = this.urlServiceImpl.getOriginUrl(shortUrl);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, url.getOriginUrl())
        );

    }
}
