package com.crud.tasks.patterns2.forum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ForumTopicTestSuite {

    private ForumTopic forumTopic;
    private ForumUser forumUser;

    @BeforeEach
    void setup() {
        forumTopic = new ForumTopic("Java Forum");
        forumUser = new ForumUser("JohnDoe");;
    }

    @Test
    void shouldAddPostToMessagesList() {
        // When
        forumTopic.addPost("Test message");

        // Then
        List<String> messages = forumTopic.getMessages();
        assertEquals(1, messages.size());
        assertEquals("Test message", messages.get(0));
    }

    @Test
    void shouldReturnCorrectTopicName() {
        // When
        String topicName = forumTopic.getName();

        // Then
        assertEquals("Java Forum", topicName);
    }

    @Test
    void shouldReturnCorrectUsername() {
        // When
        String username = forumUser.getUsername();

        // Then
        assertEquals("JohnDoe", username);
    }

    @Test
    void shouldUpdateWhenNewMessageIsAdded() {
        // Given
        forumTopic.registerObserver(forumUser);

        // When
        forumTopic.addPost("First message");

        // Then
        assertEquals(1, forumUser.getUpdateCount());
    }

    @Test
    void shouldUpdateMultipleTimesForMultiplePosts() {
        // Given
        forumTopic.registerObserver(forumUser);

        // When
        forumTopic.addPost("First message");
        forumTopic.addPost("Second message");

        // Then
        assertEquals(2, forumUser.getUpdateCount());
    }

    @Test
    void shouldNotUpdateWhenUserIsNotRegistered() {
        // Given
        // User is not registered to the forum topic

        // When
        forumTopic.addPost("First message");

        // Then
        assertEquals(0, forumUser.getUpdateCount());
    }

    @Test
    void shouldDisplayCorrectMessageCount() {
        // Given
        forumTopic.registerObserver(forumUser);

        // When
        forumTopic.addPost("First message");

        // Then
        assertEquals(1, forumTopic.getMessages().size());
        assertEquals("First message", forumTopic.getMessages().get(0));
    }
}
