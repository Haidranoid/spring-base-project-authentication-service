package com.springbaseproject.authenticationservice.fixtures;

import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;
import com.springbaseproject.sharedstarter.entities.TokenEntity;

public class AuthenticationEntityFixtures {

    public static AccountEntity adminAccountPersisted(Long id) {
        return AccountEntity.builder()
                .id(id)
                .username("bugsbunny12")
                .firstName("Bugs")
                .lastName("Bunny")
                .email("bunny@email.com")
                .password("<password>")
                .role(Roles.ADMIN)
                .enabled(true)
                .build();
    }

    public static TokenEntity accessToken() {
        return TokenEntity.builder()
                .build();
    }
}
