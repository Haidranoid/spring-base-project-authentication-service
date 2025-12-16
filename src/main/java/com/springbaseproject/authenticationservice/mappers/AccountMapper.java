package com.springbaseproject.authenticationservice.mappers;

import com.springbaseproject.authenticationservice.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.dtos.AuthResponseDto;
import com.springbaseproject.sharedstarter.entities.Account;

public interface AccountMapper {
    AuthAccountDto toAuthAccountDto(Account account);
    AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken);
}
