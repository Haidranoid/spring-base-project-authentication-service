package com.springbaseproject.authenticationservice.mappers.impl;

import com.springbaseproject.authenticationservice.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.mappers.AccountMapper;
import com.springbaseproject.sharedstarter.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AccountMapper {

    @Override
    public AuthAccountDto toAuthAccountDto(Account account) {
        return AuthAccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }

    @Override
    public AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken) {
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(accountDto)
                .build();
    }
}