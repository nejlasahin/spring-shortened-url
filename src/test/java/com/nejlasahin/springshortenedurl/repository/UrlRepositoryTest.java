package com.nejlasahin.springshortenedurl.repository;

import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    public void shouldCheckWhenUrlIdIsExists() {
        User user = User.builder().id(1L).username("testuser").password("testpass").build();
        userRepository.save(user);
        Url url = Url.builder().id(1L).shortUrl("test").originUrl("www.test.com").user(user).build();
        urlRepository.save(url);

        boolean expected = urlRepository.existsById(url.getId());

        assertTrue(expected);
    }

    @Test
    public void shouldCheckWhenUrlIdIsNotExists() {
        boolean expected = urlRepository.existsById(1L);

        assertFalse(expected);
    }
}
