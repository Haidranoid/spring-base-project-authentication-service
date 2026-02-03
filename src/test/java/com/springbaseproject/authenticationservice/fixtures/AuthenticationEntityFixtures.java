package com.springbaseproject.authenticationservice.fixtures;

import com.springbaseproject.sharedstarter.entities.Token;

public class AuthenticationEntityFixtures {

    public static Token accessToken() {
        return Token.builder()
                .build();
    }
}
