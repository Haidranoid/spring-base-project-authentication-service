package com.springbaseproject.authenticationservice.common.mappers;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.sharedstarter.entities.Account;

public interface AccountMapper {
    AuthAccountDto toAuthAccountDto(Account account);
    AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken);
}
