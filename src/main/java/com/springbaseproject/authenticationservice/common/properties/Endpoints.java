package com.springbaseproject.authenticationservice.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.endpoints")
public record Endpoints(
        String accountServiceEndpoint
) {
}
