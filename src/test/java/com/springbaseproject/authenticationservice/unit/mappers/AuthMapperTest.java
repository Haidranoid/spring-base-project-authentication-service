package com.springbaseproject.authenticationservice.unit.mappers;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.fixtures.AuthenticationDtoFixtures;
import com.springbaseproject.authenticationservice.mappers.AuthMapper;
import com.springbaseproject.authenticationservice.mappers.impl.AuthMapperImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthMapperTest {
    
    private final AuthMapper authMapper = new AuthMapperImpl();

    @Test
    void toAuthResponseDto_shouldMapAllFields_fromAuthAccountDto() {
        String accessToken = AuthenticationDtoFixtures.randomAccessToken();
        String refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
        AuthAccountDto authAccountDto = AuthenticationDtoFixtures.authAccountDto(1L);
        AuthResponseDto authResponseDto = authMapper.toAuthResponseDto(authAccountDto, accessToken, refreshToken);

        assertThat(authResponseDto.accessToken()).isEqualTo("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
        assertThat(authResponseDto.refreshToken()).isEqualTo("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
        assertThat(authResponseDto.account().username()).isEqualTo("admin");
        assertThat(authResponseDto.account().email()).isEqualTo("admin@email.com");
    }
}
