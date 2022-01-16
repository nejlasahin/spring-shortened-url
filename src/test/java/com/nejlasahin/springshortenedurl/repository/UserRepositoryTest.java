package com.nejlasahin.springshortenedurl.repository;

import com.nejlasahin.springshortenedurl.model.Url;
import com.nejlasahin.springshortenedurl.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserExistsById() {
        boolean expected = userRepository.existsById(1L);

        assertTrue(expected);
    }

    @Test
    public void testUserNotExistsById() {
        boolean expected = userRepository.existsById(5L);

        assertFalse(expected);
    }

    @Test
    public void testUserGetById() {
        User expected = userRepository.getById(1L);

        assertEquals(expected.getId(), 1L);
        assertEquals(expected.getUsername(), "username1");
        assertEquals(expected.getPassword(), "password1");
    }

    @Test
    public void testUserDeleteById() {
        userRepository.deleteById(2L);
        boolean expected = userRepository.existsById(2L);

        assertFalse(expected);
    }
}
