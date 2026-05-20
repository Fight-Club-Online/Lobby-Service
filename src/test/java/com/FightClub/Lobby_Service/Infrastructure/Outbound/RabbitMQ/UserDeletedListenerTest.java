package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.UserDeletedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDeletedListenerTest {

    @Mock
    private UserCharacterRepository userCharacterRepository;

    @InjectMocks
    private UserDeletedListener listener;

    @Test
    void testRecibirEventoDeletesUserCharacters() {
        // Arrange
        UserDeletedEvent event = new UserDeletedEvent();
        event.setUserId("user123");
        event.setDeletedAt(Instant.now());

        // Act
        listener.recibirEvento(event);

        // Assert
        verify(userCharacterRepository, times(1)).deleteByUserId("user123");
    }

    @Test
    void testRecibirEventoWithDifferentUsers() {
        // Arrange
        String[] userIds = {"user1", "user2", "user3"};

        for (String userId : userIds) {
            UserDeletedEvent event = new UserDeletedEvent();
            event.setUserId(userId);
            event.setDeletedAt(Instant.now());

            // Act
            listener.recibirEvento(event);

            // Assert
            verify(userCharacterRepository).deleteByUserId(userId);
        }
    }

    @Test
    void testRecibirEventoHandlesException() {
        // Arrange
        UserDeletedEvent event = new UserDeletedEvent();
        event.setUserId("user456");
        event.setDeletedAt(Instant.now());

        doThrow(new RuntimeException("Database error"))
                .when(userCharacterRepository).deleteByUserId("user456");

        // Act & Assert - Should not throw exception
        assertThatNoException().isThrownBy(() -> listener.recibirEvento(event));
        verify(userCharacterRepository, times(1)).deleteByUserId("user456");
    }

    @Test
    void testRecibirEventoCallsRepositoryWithCorrectUserId() {
        // Arrange
        UserDeletedEvent event = new UserDeletedEvent();
        String userId = "user789";
        event.setUserId(userId);
        event.setDeletedAt(Instant.now());

        // Act
        listener.recibirEvento(event);

        // Assert
        verify(userCharacterRepository, times(1)).deleteByUserId(userId);
        verifyNoMoreInteractions(userCharacterRepository);
    }

    @Test
    void testRecibirEventoWithNullUserId() {
        // Arrange
        UserDeletedEvent event = new UserDeletedEvent();
        event.setUserId(null);
        event.setDeletedAt(Instant.now());

        // Act
        listener.recibirEvento(event);

        // Assert
        verify(userCharacterRepository, times(1)).deleteByUserId(null);
    }

    @Test
    void testRecibirEventoMultipleCalls() {
        // Arrange
        for (int i = 0; i < 5; i++) {
            UserDeletedEvent event = new UserDeletedEvent();
            event.setUserId("user" + i);
            event.setDeletedAt(Instant.now());

            // Act
            listener.recibirEvento(event);

            // Assert
            verify(userCharacterRepository).deleteByUserId("user" + i);
        }
    }
}
