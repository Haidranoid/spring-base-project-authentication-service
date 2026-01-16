package com.springbaseproject.authenticationservice.configuration;

import com.springbaseproject.authenticationservice.properties.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientsConfig {

    private final Endpoints endpoints;

    @Bean
    public WebClient accountWebClient() {
        return WebClient.builder()
                .baseUrl(endpoints.accountServiceEndpoint())
                .build();
    }
}
