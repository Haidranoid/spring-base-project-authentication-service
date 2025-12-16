package com.springbaseproject.authenticationservice.services;

import com.intellisense.sienmat.mappers.UsersMapper;
import com.intellisense.sienmat.mocks.repositories.UserRepositoryMocks;
import com.intellisense.sienmat.repositories.UserRepository;
import com.intellisense.sienmat.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsersServiceRealUsersMapperTest {
    @Mock
    private UserRepository userRepository;

    private final UsersMapper usersMapper = new UsersMapper();
    private UserService userService;

    @BeforeEach
    void setup(){
        userService = new UserServiceImpl(null, userRepository, usersMapper, null);
    }

    @Test
    public void whenUserRepository_findAll_shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(UserRepositoryMocks.getEmptyUsersList());

        var users = userService.findAll();

        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void whenUserRepository_findAll_shouldReturnTwoUsers() {
        var mockedUsers = UserRepositoryMocks.getTwoMockedUsers();
        when(userRepository.findAll()).thenReturn(mockedUsers);

        var users = userService.findAll();

        assertEquals(2, users.size());
    }
}