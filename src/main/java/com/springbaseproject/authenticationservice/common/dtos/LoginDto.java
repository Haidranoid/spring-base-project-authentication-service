package com.springbaseproject.authenticationservice.common.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record LoginDto(
       @NonNull String username,
       @NonNull String password
) {
    @NonNull
    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                '}';
    }
}
