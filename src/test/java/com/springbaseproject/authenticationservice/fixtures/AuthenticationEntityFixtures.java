package com.springbaseproject.authenticationservice.fixtures;

import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;
import com.springbaseproject.sharedstarter.entities.TokenEntity;

public class AuthenticationEntityFixtures {

    public static AccountEntity currentSession() {
        return AccountEntity.builder()
                .username("admin")
                .firstName("Steve")
                .lastName("Rogers")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .role(Roles.ADMIN)
                .build();
    }

    public static TokenEntity accessToken() {
        return TokenEntity.builder()
                .build();
    }
}
