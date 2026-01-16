package com.springbaseproject.authenticationservice.data.repositories;

import com.springbaseproject.authenticationservice.fixtures.AuthenticationFixtures;
import com.springbaseproject.authenticationservice.repositories.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TokenRepositoryTest {

    @Autowired
    TokenRepository tokenRepository;

    @Test
    void save_shouldPersistEntity(){
        var token = AuthenticationFixtures.accessToken();
        tokenRepository.save(token);

        var tokens = tokenRepository.findAll();

        assertThat(tokens).hasSize(1);
    }
}
