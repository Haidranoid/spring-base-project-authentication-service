package com.springbaseproject.authenticationservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbaseproject.authenticationservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.authenticationservice.controllers.AuthenticationController;
import com.springbaseproject.authenticationservice.controllers.advices.AuthenticationExceptionHandler;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({
        AuthenticationExceptionHandler.class,
        GlobalExceptionHandler.class
})
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("POST /api/v1/auth/signup returns 200 when account is created")
    public void signup_whenExists_shouldReturn200() {
        //TODO
    }
}
