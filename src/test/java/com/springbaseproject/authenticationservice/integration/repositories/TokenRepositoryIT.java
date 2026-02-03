package com.springbaseproject.authenticationservice.integration.repositories;

import com.springbaseproject.authenticationservice.fixtures.AuthenticationEntityFixtures;
import com.springbaseproject.authenticationservice.containers.base.AbstractPostgresIT;
import com.springbaseproject.authenticationservice.repositories.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TokenRepositoryIT extends AbstractPostgresIT {

    @Autowired
    TokenRepository tokenRepository;

    //@Test
    void save_shouldPersistEntity(){
        var token = AuthenticationEntityFixtures.accessToken();
        tokenRepository.save(token);

        var tokens = tokenRepository.findAll();

        assertThat(tokens).hasSize(1);
    }
}
