package com.springbaseproject.authenticationservice.mocks.repositories;

import com.intellisense.sienmat.entities.UserEntity;
import com.intellisense.sienmat.mocks.factories.UsersMockFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMocks {
    public static final Logger logger = LoggerFactory.getLogger(UserRepositoryMocks.class);

    public static List<UserEntity> getTwoMockedUsers() {
        List<UserEntity> userEntities = new ArrayList<>();

        userEntities.add(UsersMockFactory.userMockedOne());
        userEntities.add(UsersMockFactory.userMockedTwo());

        return userEntities;
    }

    public static List<UserEntity> getEmptyUsersList() {
        return new ArrayList<>();
    }
}
