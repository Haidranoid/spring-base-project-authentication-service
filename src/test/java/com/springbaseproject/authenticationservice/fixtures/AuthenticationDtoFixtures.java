package com.springbaseproject.authenticationservice.fixtures;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.common.dtos.LoginDto;
import com.springbaseproject.authenticationservice.common.dtos.SignupDto;
import com.springbaseproject.sharedstarter.constants.Roles;

public class AuthenticationDtoFixtures {

    public static AuthAccountDto authAccountDto(Long id) {
        return AuthAccountDto.builder()
                .id(id)
                .username("admin")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build();
    }

    public static AuthAccountDto loginAuthAccountDto(Long id) {
        return AuthAccountDto.builder()
                .id(id)
                .username("admin")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build();
    }

    public static AuthAccountDto signupAuthAccountDto(Long id) {
        return AuthAccountDto.builder()
                .id(id)
                .username("manager")
                .firstName("Pedro")
                .lastName("Pascal")
                .email("admin@email.com")
                .role(Roles.MANAGER)
                .build();
    }

    public static LoginDto loginWithUsernameAndPassword() {
        return LoginDto.builder()
                .username("admin")
                .password("<PASSWORD>")
                .build();
    }

    public static SignupDto managerSignupDto() {
        return SignupDto.builder()
                .username("manager")
                .firstName("Pedro")
                .lastName("Pascal")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Roles.MANAGER)
                .build();
    }

    //TODO: improve it with taker
    public static String randomAccessToken() {
        return "access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj";
    }

    //TODO: improve it with taker
    public static String randomRefreshToken() {
        return "refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj";
    }

    public static AuthResponseDto loginAuthResponseDto(Long id) {
        return AuthResponseDto.builder()
                .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
                .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
                .account(
                        AuthAccountDto.builder()
                                .id(id)
                                .username("admin")
                                .email("admin@email.com")
                                .firstName("Steve")
                                .lastName("Rogers")
                                .role(Roles.ADMIN)
                                .build()
                )
                .build();
    }

    public static AuthResponseDto signupAuthResponseDto(Long id) {
        return AuthResponseDto.builder()
                .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
                .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
                .account(
                        AuthAccountDto.builder()
                                .id(id)
                                .username("manager")
                                .email("manager@email.com")
                                .firstName("Pedro")
                                .lastName("Pascal")
                                .role(Roles.MANAGER)
                                .build()
                )
                .build();
    }
}
