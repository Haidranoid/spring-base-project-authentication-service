package com.springbaseproject.authenticationservice.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthResponseDto(
        @NonNull String accessToken,
        @NonNull String refreshToken,
        @NonNull AuthAccountDto account
) {
    @NonNull
    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "accessToken='" + "[accessToken]" + '\'' +
                ", refreshToken='" + "[refreshToken]" + '\'' +
                ", account=" + account +
                '}';
    }
}