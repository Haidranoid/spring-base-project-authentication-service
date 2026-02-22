package com.springbaseproject.authenticationservice.services.impl;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.common.dtos.LoginDto;
import com.springbaseproject.authenticationservice.common.dtos.SignupDto;
import com.springbaseproject.authenticationservice.common.utils.JwtSignerService;
import com.springbaseproject.authenticationservice.mappers.impl.AuthMapperImpl;
import com.springbaseproject.authenticationservice.common.properties.Endpoints;
import com.springbaseproject.authenticationservice.repositories.TokenRepository;
import com.springbaseproject.authenticationservice.services.AuthenticationService;
import com.springbaseproject.sharedstarter.constants.Permissions;
import com.springbaseproject.authenticationservice.common.constants.TokenTypes;
import com.springbaseproject.authenticationservice.common.entities.TokenEntity;
import com.springbaseproject.sharedstarter.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtSignerService jwtSignerService;
    private final RestTemplate restTemplate;
    private final Endpoints endpoints;
    private final SessionUtils sessionUtils;
    private final TokenRepository tokenRepository;
    private final AuthMapperImpl authMapper;

    @Override
    public AuthResponseDto login(LoginDto loginDto) {

        var accountAuthenticated = restTemplate.postForObject(
                endpoints.accountServiceInternalEndpoint() + "/authenticate-login",
                loginDto,
                AuthAccountDto.class
        );

        if (accountAuthenticated == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }

        var roles = List.of(accountAuthenticated.role().name());

        var scopes = accountAuthenticated.role()
                .getPermissions()
                .stream()
                .map(Permissions::getPermission)
                .toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("scope", String.join(" ", scopes));

        var accessToken = jwtSignerService.generateAccessToken(
                accountAuthenticated.username(),
                claims
        );

        var refreshToken = jwtSignerService.generateRefreshToken(
                accountAuthenticated.username()
        );

        return authMapper.toAuthResponseDto(accountAuthenticated, accessToken, refreshToken);
    }

    @Override
    public AuthResponseDto signup(SignupDto signupDto) {

        var accountCreated = restTemplate.postForObject(
                endpoints.accountServiceInternalEndpoint(),
                signupDto,
                AuthAccountDto.class
        );

        if (accountCreated == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }

        var accessToken = jwtSignerService.generateAccessToken(accountCreated.username(), new HashMap<>());
        var refreshToken = jwtSignerService.generateRefreshToken(accountCreated.username());

        saveAccountToken(accountCreated.id(), accessToken);

        return authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken);
    }

    /*
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String refreshToken = authHeader.split(" ")[1].trim();
        if (jwtService.isExpired(refreshToken)) {
            return;
        }

        final String username = jwtService.decode(refreshToken).getSubject();
        if (username == null) {
            return;
        }

        // var dbAccount = accountHttpClient.find(username);

        var account = restTemplate.getForObject(
                endpoints.accountServiceEndpoint() + "/" + username,
                AuthAccountDto.class
        );

        if (account == null) {
            return;
        }

        if (!(username.equals(account.username()))) {
            return;
        }

        var accessToken = jwtService.generateAccessToken(account.username());

        revokeAllAccountTokensById(account.id());
        saveAccountToken(account.id(), accessToken);

        var refreshTokenRequestResponse = AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(account)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), refreshTokenRequestResponse);
    }

    */

    private void saveAccountToken(Long accountId, String jwtToken) {
        var token = TokenEntity.builder()
                .accountId(accountId)
                .token(jwtToken)
                .tokenType(TokenTypes.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    /*
    private void revokeAllAccountTokensById(Long accountId) {
        var validUserTokens = tokenRepository.findAllValidTokensByAccountId(accountId);

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

     */
}
