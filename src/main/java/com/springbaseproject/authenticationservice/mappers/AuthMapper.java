package com.springbaseproject.authenticationservice.mappers;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;

public interface AuthMapper {
    //AuthAccountDto toAuthAccountDto(Jwt account);
    AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken);
}
