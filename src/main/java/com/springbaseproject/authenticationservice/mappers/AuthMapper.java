package com.springbaseproject.authenticationservice.mappers;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.sharedstarter.entities.AccountEntity;

public interface AuthMapper {
    AuthAccountDto toAuthAccountDto(AccountEntity account);
    AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken);
}
