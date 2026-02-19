package com.springbaseproject.authenticationservice.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbaseproject.authenticationservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.authenticationservice.controllers.AuthenticationController;
import com.springbaseproject.authenticationservice.controllers.advices.AuthenticationExceptionHandler;
import com.springbaseproject.authenticationservice.fixtures.AuthenticationDtoFixtures;
import com.springbaseproject.authenticationservice.services.impl.AuthenticationServiceImpl;
import com.springbaseproject.sharedstarter.constants.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    @DisplayName("GET /api/v1/auth/me returns 200 when there user has a session")
    public void me_whenIsThereASessionActive_shouldReturn202() throws Exception {
        var session = AuthenticationDtoFixtures.meAuthResponseDto(1L);

        when(authenticationService.me())
                .thenReturn(session);

        mockMvc.perform(get("/api/v1/auth/me"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("ronald"))
                .andExpect(jsonPath("$.role").value(Roles.USER.name()));
    }

    @Test
    @DisplayName("POST /api/v1/auth/login returns 200 when account credentials are valid")
    public void login_whenAccountCredentialsAreValid_shouldReturn202() throws Exception {
        var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();
        var accountLoggedIn = AuthenticationDtoFixtures.loginAuthResponseDto(1L);

        when(authenticationService.login(loginDto))
                .thenReturn(accountLoggedIn);

        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginDto))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.accessToken").value("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
                .andExpect(jsonPath("$.refreshToken").value("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
                .andExpect(jsonPath("$.account.id").value(1L))
                .andExpect(jsonPath("$.account.email").value("admin@email.com"))
                .andExpect(jsonPath("$.account.role").value(Roles.ADMIN.name()));
    }

    @Test
    @DisplayName("POST /api/v1/auth/signup returns 201 when account was created successfully")
    public void signup_whenAccountWasCreated_shouldReturn200() throws Exception {
        var signupDto = AuthenticationDtoFixtures.managerSignupDto();
        var accountCreated = AuthenticationDtoFixtures.signupAuthResponseDto(1L);

        when(authenticationService.signup(signupDto))
                .thenReturn(accountCreated);

        mockMvc.perform(
                        post("/api/v1/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").value("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
                .andExpect(jsonPath("$.refreshToken").value("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
                .andExpect(jsonPath("$.account.id").value(1L))
                .andExpect(jsonPath("$.account.email").value("manager@email.com"))
                .andExpect(jsonPath("$.account.role").value(Roles.MANAGER.name()));
    }
}
