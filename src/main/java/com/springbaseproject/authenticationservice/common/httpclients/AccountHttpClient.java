package com.springbaseproject.authenticationservice.common.httpclients;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
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
