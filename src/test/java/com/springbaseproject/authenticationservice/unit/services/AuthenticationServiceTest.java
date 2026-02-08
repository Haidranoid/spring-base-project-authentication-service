package com.springbaseproject.authenticationservice.unit.services;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.exceptions.UnauthorizedException;
import com.springbaseproject.authenticationservice.fixtures.AuthenticationDtoFixtures;
import com.springbaseproject.authenticationservice.fixtures.AuthenticationEntityFixtures;
import com.springbaseproject.authenticationservice.mappers.impl.AuthMapperImpl;
import com.springbaseproject.authenticationservice.properties.Endpoints;
import com.springbaseproject.authenticationservice.repositories.TokenRepository;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import com.springbaseproject.sharedstarter.constants.Permissions;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.services.JwtService;
import com.springbaseproject.sharedstarter.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Endpoints endpoints;
    @Mock
    private SecurityUtils securityUtils;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private AuthMapperImpl authMapper;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void me_whenThereIsASession_shouldReturnAuthAccountDto() {
        var currentSession = AuthenticationEntityFixtures.currentSession();
        var authAccountDto = AuthenticationDtoFixtures.authAccountDto(1L);

        when(securityUtils.getCurrentAccount())
                .thenReturn(currentSession);
        when(authMapper.toAuthAccountDto(currentSession))
                .thenReturn(authAccountDto);

        var session = authenticationService.me();

        assertNotNull(session);
        assertEquals(1L, session.id());
        assertEquals("admin", session.username());
        assertEquals("admin@email.com", session.email());
        assertEquals(Roles.ADMIN, session.role());
    }

    @Test
    public void me_whenThereIsNotASession_shouldThrowException() {
        when(securityUtils.getCurrentAccount())
                .thenReturn(null);

        var exception = assertThrows(UnauthorizedException.class,
                () -> authenticationService.me());

        assertEquals("Invalid username or password", exception.getMessage());
    }

    @Test
    public void login_whenCredentialsAreValid_shouldReturnAuthResponseDto() {
        var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();
        var accountAuthenticated = AuthenticationDtoFixtures.loginAuthAccountDto(1L);
        var accessToken = AuthenticationDtoFixtures.randomAccessToken();
        var refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
        var authResponseDto = AuthenticationDtoFixtures.loginAuthResponseDto(1L);

        var roles = List.of(accountAuthenticated.role().name());
        var scopes = accountAuthenticated.role().getPermissions().stream().map(Permissions::getPermission).toList();

        when(restTemplate.postForObject(
                endpoints.accountServiceInternalEndpoint() + "/authenticate-login",
                loginDto,
                AuthAccountDto.class)
        ).thenReturn(accountAuthenticated);


        when(jwtService.generateAccessToken(accountAuthenticated.username(), roles, scopes))
                .thenReturn(accessToken);

        when(jwtService.generateRefreshToken(accountAuthenticated.username()))
                .thenReturn(refreshToken);

        when(authMapper.toAuthResponseDto(accountAuthenticated, accessToken, refreshToken))
                .thenReturn(authResponseDto);

        var session = authenticationService.login(loginDto);

        assertEquals(accessToken, session.accessToken());
        assertEquals(refreshToken, session.refreshToken());
        assertEquals(loginDto.username(), session.account().username());
    }

    @Test
    public void login_whenCredentialsAreInvalid_shouldThrowException() {
        var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();

        when(restTemplate.postForObject(
                endpoints.accountServiceInternalEndpoint() + "/authenticate-login",
                loginDto,
                AuthAccountDto.class)
        ).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password"));

        var exception = assertThrows(ResponseStatusException.class,
                () -> authenticationService.login(loginDto));

        assertEquals("400 BAD_REQUEST \"Invalid username or password\"", exception.getMessage());
    }

    @Test
    public void signup_whenUserIsNotDuplicated_shouldReturnAuthResponseDto() {
        var signupDto = AuthenticationDtoFixtures.managerSignupDto();
        var accountCreated = AuthenticationDtoFixtures.signupAuthAccountDto(1L);
        var accessToken = AuthenticationDtoFixtures.randomAccessToken();
        var refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
        var authResponseDto = AuthenticationDtoFixtures.signupAuthResponseDto(1L);

        when(restTemplate.postForObject(
                endpoints.accountServiceInternalEndpoint(),
                signupDto,
                AuthAccountDto.class)
        ).thenReturn(accountCreated);


        when(jwtService.generateAccessToken(accountCreated.username()))
                .thenReturn(accessToken);

        when(jwtService.generateRefreshToken(accountCreated.username()))
                .thenReturn(refreshToken);

        when(authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken))
                .thenReturn(authResponseDto);

        var account = authenticationService.signup(signupDto);

        assertEquals(accessToken, account.accessToken());
        assertEquals(refreshToken, account.refreshToken());
        assertEquals(signupDto.username(), account.account().username());
    }

    @Test
    public void signup_whenUserIsDuplicated_shouldThrowException() {
    }

    @Test
    public void saveAccountToken_whenSignupWasSuccessful_shouldPersistTheToken() {
    }

    @Test
    public void revokeAllAccountTokensById_whenRefreshTokenWasSuccessful_shouldRevokeAllPreviousTokens() {
    }

    @Test
    public void refreshToken_whenAccessTokenIsExpired_shouldReturnAuthResponseDto() {
    }
}