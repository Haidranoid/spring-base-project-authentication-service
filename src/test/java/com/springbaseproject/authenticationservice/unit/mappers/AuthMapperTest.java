package com.springbaseproject.authenticationservice.unit.mappers;

import com.springbaseproject.authenticationservice.mappers.AuthMapper;
import com.springbaseproject.authenticationservice.mappers.impl.AuthMapperImpl;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthMapperTest {
    
    private final AuthMapper mapper = new AuthMapperImpl();

    @Test
    void toDto_shouldMapAllFields() {
    }
}
