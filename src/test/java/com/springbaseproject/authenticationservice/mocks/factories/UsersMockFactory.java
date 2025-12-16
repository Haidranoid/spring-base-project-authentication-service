package com.springbaseproject.authenticationservice.mocks.factories;

import com.intellisense.sienmat.constants.Disability;
import com.intellisense.sienmat.constants.Role;
import com.intellisense.sienmat.dto.UserDTO;
import com.intellisense.sienmat.entities.UserEntity;

public class UsersMockFactory {

        public static UserEntity userMockedOne() {
                return UserEntity.builder()
                        .id(1)
                        .username("fake@email.com")
                        .email("fake@email.com")
                        .password("<PASSWORD>")
                        .firstName("Steve")
                        .lastName("Rogers")
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .disability(Disability.VISUAL)
                        .role(Role.STUDENT)
                        .build();
        }

        public static UserEntity userMockedTwo() {
                return UserEntity.builder()
                        .id(2)
                        .username("fake2@email.com")
                        .email("fake2@email.com")
                        .password("<PASSWORD>")
                        .firstName("Dooms")
                        .lastName("Days")
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .disability(Disability.HEARING)
                        .role(Role.ADMIN)
                        .build();
        }

        public static UserDTO userDtoMockedOne() {
                return UserDTO.builder()
                        .id(1)
                        .email("fake1@email.com")
                        .password("<PASSWORD>")
                        .firstName("Dooms")
                        .lastName("Days")
                        .disability(Disability.HEARING)
                        .role(Role.ADMIN)
                        .build();
        }
}
