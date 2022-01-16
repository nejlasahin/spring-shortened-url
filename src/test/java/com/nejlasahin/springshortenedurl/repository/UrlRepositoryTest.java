package com.nejlasahin.springshortenedurl.repository;

import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UrlRepositoryTest {

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void tearDown(){
        urlRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testUrlExistsById() {
        boolean expected = urlRepository.existsById(1L);

        assertTrue(expected);
    }

    @Test
    public void testUrlNotExistsById() {
        boolean expected = urlRepository.existsById(10L);

        assertFalse(expected);
    }

    @Test
    public void testUrlGetById() {
        Url expected = urlRepository.getById(1L);

        assertEquals(expected.getId(), 1L);
        assertEquals(expected.getOriginUrl(), "origin1");
        assertEquals(expected.getShortUrl(), "short1");
    }

    @Test
    public void testUrlDeleteById() {
        urlRepository.deleteById(1L);
        boolean expected = urlRepository.existsById(1L);

        assertFalse(expected);
    }

    @Test
    public void testUrlFindAllById() {
        List<Url> expected = urlRepository.findAllById(1L);

        assertEquals(expected.size(), 3);
        assertEquals(expected.get(0).getId(), 1);
        assertEquals(expected.get(1).getId(), 2);
        assertEquals(expected.get(2).getId(), 3);

    }
}
