package com.springbaseproject.authenticationservice.common.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record SignupDto(
       @NonNull String username,
       @NonNull String password
) {
    @NonNull
    @Override
    public String toString() {
        return "SignupDto{" +
                "username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                '}';
    }
}
