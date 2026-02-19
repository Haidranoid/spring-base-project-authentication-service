package com.springbaseproject.authenticationservice.mappers.impl;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.mappers.AuthMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {

    @Override
    public AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken) {
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(accountDto)
                .build();
    }
}