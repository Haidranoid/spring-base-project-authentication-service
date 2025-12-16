package com.springbaseproject.authenticationservice.services;

import com.intellisense.sienmat.dto.UserDTO;
import com.intellisense.sienmat.mappers.UsersMapper;
import com.intellisense.sienmat.mocks.repositories.UserRepositoryMocks;
import com.intellisense.sienmat.repositories.UserRepository;
import com.intellisense.sienmat.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UsersMapper usersMapper; // mocking the user's mapper

    @InjectMocks
    private UserServiceImpl userService;

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

        mockedUsers.forEach(user -> {
            when(usersMapper.toDTO(user)).thenReturn(UserDTO.builder().build());
        });

        var users = userService.findAll();

        assertEquals(2, users.size());
    }
}