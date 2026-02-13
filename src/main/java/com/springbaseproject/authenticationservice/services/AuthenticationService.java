package com.springbaseproject.authenticationservice.services;


import com.springbaseproject.authenticationservice.common.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.common.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.common.dtos.LoginDto;
import com.springbaseproject.authenticationservice.common.dtos.SignupDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthAccountDto me();
    AuthResponseDto login(LoginDto loginDto);
    AuthResponseDto signup(SignupDto signupDto);
    //void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
