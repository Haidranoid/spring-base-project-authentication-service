package com.springbaseproject.authenticationservice.fixtures;

import com.springbaseproject.sharedstarter.entities.TokenEntity;

public class AuthenticationEntityFixtures {

    public static TokenEntity accessToken() {
        return TokenEntity.builder()
                .build();
    }
}
