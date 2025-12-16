package com.springbaseproject.authenticationservice.services;

import com.intellisense.sienmat.dto.TopicDTO;
import com.intellisense.sienmat.mappers.TopicsMapper;
import com.intellisense.sienmat.mocks.repositories.TopicRepositoryMocks;
import com.intellisense.sienmat.repositories.TopicRepository;
import com.intellisense.sienmat.services.impl.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TopicServiceTest {
    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicsMapper topicsMapper; // mocking the user's mapper

    @InjectMocks
    private TopicServiceImpl topicServiceImpl;

    @Test
    public void whenTopicRepository_findAll_shouldReturnEmptyList() {
        when(topicRepository.findAll()).thenReturn(TopicRepositoryMocks.getEmptyUsersList());

        var users = topicServiceImpl.findAll();

        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void whenTopicRepository_findAll_shouldReturnThreeTopics() {
        var mockedTopics = TopicRepositoryMocks.getThreeMockedTopics();
        when(topicRepository.findAll()).thenReturn(mockedTopics);

        mockedTopics.forEach(topic -> {
            when(topicsMapper.toDTO(topic)).thenReturn(TopicDTO.builder().build());
        });

        var topics = topicServiceImpl.findAll();

        assertEquals(3, topics.size());
    }
}