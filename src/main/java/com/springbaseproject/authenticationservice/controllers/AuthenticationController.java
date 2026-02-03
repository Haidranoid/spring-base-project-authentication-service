package com.springbaseproject.authenticationservice.controllers;

import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.common.dtos.LoginDto;
import com.springbaseproject.authenticationservice.common.dtos.SignupDto;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Tag(name = "Authentication Controller", description = "requires [Account Service]")
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @SecurityRequirement(name = "bearerAuth")
    public AuthAccountDto getMe() {
        log.info("getMe request started");

        var currentSession = authenticationService.me();

        log.info("getMe request response: {}", currentSession);
        return currentSession;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponseDto login(@RequestBody LoginDto loginDto) {
        log.info("login request body: {}", loginDto);

        var loginResponse = authenticationService.login(loginDto);

        log.info("login request response: {}", loginResponse);
        return loginResponse;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto signup(@RequestBody SignupDto signupDto) {
        log.info("signup request body: {}", signupDto);

        var signupResponse = authenticationService.signup(signupDto);

        log.info("signup request response: {}", signupResponse);
        return signupResponse;
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "bearerAuth")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
