package com.springbaseproject.authenticationservice.mocks.factories;

import com.intellisense.sienmat.constants.Topics;
import com.intellisense.sienmat.entities.TopicEntity;

public class TopicMockFactory {

        public static TopicEntity topicBasicMocked() {
                return TopicEntity.builder()
                        .id(1)
                        .topic(Topics.BASIC)
                        .description("basic description mock")
                        .build();
        }

        public static TopicEntity topicIntermediateMocked() {
                return TopicEntity.builder()
                        .id(2)
                        .topic(Topics.INTERMEDIATE)
                        .description("intermediate description mock")
                        .build();
        }

        public static TopicEntity topicAdvancedMocked() {
                return TopicEntity.builder()
                        .id(3)
                        .topic(Topics.ADVANCED)
                        .description("advanced description mock")
                        .build();
        }
}
