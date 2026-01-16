package com.springbaseproject.authenticationservice.web.controllers;

import com.springbaseproject.authenticationservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.authenticationservice.controllers.internals.advices.InternalAuthenticationExceptionHandler;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(InternalAuthenticationControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({
        InternalAuthenticationExceptionHandler.class,
        GlobalExceptionHandler.class
})
public class InternalAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("GET /accounts/{id} returns 200 when account exists")
    public void getAccountById_whenExists_shouldReturn200() {
    }
}
