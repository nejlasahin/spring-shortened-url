package com.nejlasahin.springshortenedurl.controller;

import com.nejlasahin.springshortenedurl.dto.request.UrlRequestDto;
import com.nejlasahin.springshortenedurl.dto.response.UrlResponseDto;
import com.nejlasahin.springshortenedurl.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {

    @Mock
    UrlService urlService;

    @InjectMocks
    UrlController urlController;

    @Test
    public void redirect(){
        String shortUrl = "test";
        String originUrl = "www.test.com";

        when(urlService.getOriginUrl(shortUrl)).thenReturn(originUrl);

        ResponseEntity<?> actual = urlController.redirect(shortUrl);

        assertAll(
                () -> assertEquals(actual.getStatusCode(), HttpStatus.MOVED_PERMANENTLY)
        );
    }

    @Test
    public void save(){
        Long userId = 1L;
        String originUrl = "www.test.com";

        UrlRequestDto urlRequestDto = UrlRequestDto.builder().originUrl(originUrl).build();
        UrlResponseDto urlResponseDto = UrlResponseDto.builder().id(1L).shortUrl("test").build();

        when(urlService.save(urlRequestDto, userId)).thenReturn(urlResponseDto);

        ResponseEntity<UrlResponseDto> actual = urlController.save(urlRequestDto, userId);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals(urlResponseDto.getShortUrl(), actual.getBody().getShortUrl()),
                () -> assertEquals(urlResponseDto, actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }

    @Test
    public void getAll(){
        Long userId = 1L;

        UrlResponseDto urlResponseDto1 = UrlResponseDto.builder().id(1L).shortUrl("test1").build();
        UrlResponseDto urlResponseDto2 = UrlResponseDto.builder().id(2L).shortUrl("test2").build();

        List<UrlResponseDto> urlResponseDtoList= new ArrayList<>(Arrays.asList(urlResponseDto1, urlResponseDto2));

        when(urlService.getAll(userId)).thenReturn(urlResponseDtoList);

        ResponseEntity<List<UrlResponseDto>> actual = urlController.getAll(userId);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals(urlResponseDtoList.size(), actual.getBody().size()),
                () -> assertEquals(urlResponseDtoList, actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }

    @Test
    public void getById(){
        Long userId = 1L;
        Long urlId = 1L;

        UrlResponseDto urlResponseDto = UrlResponseDto.builder().id(urlId).shortUrl("test").build();

        when(urlService.getById(userId, urlId)).thenReturn(urlResponseDto);

        ResponseEntity<UrlResponseDto> actual = urlController.getById(userId, urlId);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals(urlResponseDto.getShortUrl(), actual.getBody().getShortUrl()),
                () -> assertEquals(urlResponseDto, actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }

    @Test
    public void deleteById(){
        Long userId = 1L;
        Long urlId = 1L;

        ResponseEntity<?> actual = urlController.deleteById(userId, urlId);

        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals("Url is deleted.", actual.getBody()),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK )
        );
    }
}
