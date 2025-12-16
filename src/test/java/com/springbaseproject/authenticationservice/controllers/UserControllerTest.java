package com.springbaseproject.authenticationservice.controllers;

import com.intellisense.sienmat.configuration.TestSecurityConfig;
import com.intellisense.sienmat.mocks.factories.UsersMockFactory;
import com.intellisense.sienmat.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private UserServiceImpl userService;

    @Test
    public void shouldReturnGreeting() throws Exception {
        var userDTO = UsersMockFactory.userDtoMockedOne();
        var userOptionalMocked = Optional.of(userDTO);

        when(userService.findById(1)).thenReturn(userOptionalMocked);

        assertThat(mockMvcTester.get().uri("/api/v1/users/1")).hasStatusOk();
    }
}
