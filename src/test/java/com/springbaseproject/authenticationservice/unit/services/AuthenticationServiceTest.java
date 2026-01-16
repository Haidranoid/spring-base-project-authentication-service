package com.springbaseproject.authenticationservice.unit.services;

import com.springbaseproject.authenticationservice.mappers.AuthMapper;
import com.springbaseproject.authenticationservice.repositories.TokenRepository;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private AuthMapper authMapper;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void whenUserRepository_findAll_shouldReturnEmptyList() {
    }
}