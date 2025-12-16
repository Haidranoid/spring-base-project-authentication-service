package com.springbaseproject.authenticationservice.mocks.repositories;

import com.intellisense.sienmat.entities.TopicEntity;
import com.intellisense.sienmat.mocks.factories.TopicMockFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TopicRepositoryMocks {
    public static final Logger logger = LoggerFactory.getLogger(TopicRepositoryMocks.class);

    public static List<TopicEntity> getTwoMockedTopics() {
        List<TopicEntity> topicEntities = new ArrayList<>();

        topicEntities.add(TopicMockFactory.topicBasicMocked());
        topicEntities.add(TopicMockFactory.topicIntermediateMocked());

        return topicEntities;
    }

    public static List<TopicEntity> getThreeMockedTopics() {
        List<TopicEntity> topicEntities = new ArrayList<>();

        topicEntities.add(TopicMockFactory.topicBasicMocked());
        topicEntities.add(TopicMockFactory.topicIntermediateMocked());
        topicEntities.add(TopicMockFactory.topicAdvancedMocked());

        return topicEntities;
    }

    public static List<TopicEntity> getEmptyUsersList() {
        return new ArrayList<>();
    }
}
