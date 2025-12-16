package com.springbaseproject.authenticationservice.httpclients;

import com.springbaseproject.authenticationservice.dtos.AuthAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AccountHttpClient {

    private final WebClient accountWebClient;

    public AuthAccountDto findByUsername(String username) {
        return accountWebClient
                .get()
                .uri("/{username}", username)
                .retrieve()
                .bodyToMono(AuthAccountDto.class)
                .block(); // allows sync mode
    }
}
