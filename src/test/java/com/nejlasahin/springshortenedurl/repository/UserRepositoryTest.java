package com.nejlasahin.springshortenedurl.repository;

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

    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void shouldCheckWhenUserUsernameIsExists() {
        User user = User.builder().id(1L).username("testuser").password("testpass").build();
        userRepository.save(user);

        boolean expected = userRepository.existsById(user.getId());

        assertTrue(expected);
    }

    @Test
    public void shouldCheckWhenUserUsernameIsNotExists() {
        boolean expected = userRepository.existsById(1L);

        assertFalse(expected);
    }
}
