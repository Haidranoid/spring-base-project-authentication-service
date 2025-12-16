package com.springbaseproject.authenticationservice.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthAccountDto(
        @NonNull Long id,
        @NonNull String username,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull Roles role
) {
    @NonNull
    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}