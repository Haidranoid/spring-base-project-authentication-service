package com.springbaseproject.authenticationservice.controllers;

import com.springbaseproject.authenticationservice.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.dtos.LoginDto;
import com.springbaseproject.authenticationservice.dtos.SignupDto;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    @Tag(name = "Authentication Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AuthAccountDto getMe() {
        logger.info("getMe request started");

        var currentSession = authenticationService.me();

        logger.info("getMe request response: {}", currentSession);
        return currentSession;
    }

    @PostMapping("/login")
    @Tag(name = "Authentication Controller", description = "requires [Account Service]")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AuthResponseDto login(@RequestBody LoginDto loginDto) {
        logger.info("login request body: {}", loginDto);

        var loginResponse = authenticationService.login(loginDto);

        logger.info("login request response: {}", loginResponse);
        return loginResponse;
    }

    @PostMapping("/signup")
    @Tag(name = "Authentication Controller", description = "requires [Account Service]")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AuthResponseDto signup(@RequestBody SignupDto signupDto) {
        logger.info("signup request body: {}", signupDto);

        var signupResponse = authenticationService.signup(signupDto);

        logger.info("signup request response: {}", signupResponse);
        return signupResponse;
    }

    // ============================================================================== //
    @PostMapping("/refresh-token")
    @SecurityRequirement(name = "bearerAuth")
    @Tag(name = "Authentication Controller")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
