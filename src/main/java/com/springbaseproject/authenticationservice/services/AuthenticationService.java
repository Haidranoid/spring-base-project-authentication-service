package com.springbaseproject.authenticationservice.services;


import com.springbaseproject.authenticationservice.dtos.AuthAccountDto;
import com.springbaseproject.authenticationservice.dtos.AuthResponseDto;
import com.springbaseproject.authenticationservice.dtos.LoginDto;
import com.springbaseproject.authenticationservice.dtos.SignupDto;

public interface AuthenticationService {
    AuthAccountDto me();
    AuthResponseDto login(LoginDto loginDto);
    AuthResponseDto signup(SignupDto signupDto);
}
