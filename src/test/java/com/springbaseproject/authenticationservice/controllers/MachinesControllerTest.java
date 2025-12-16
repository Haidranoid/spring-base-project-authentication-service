package com.springbaseproject.authenticationservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MachinesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MachinesControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnGreeting() throws Exception {
        //when(userService.findById(1)).thenReturn(userOptionalMocked);

        mockMvc.perform(get("/api/v1/machines"))
                .andExpect(status().isOk());
    }
}
