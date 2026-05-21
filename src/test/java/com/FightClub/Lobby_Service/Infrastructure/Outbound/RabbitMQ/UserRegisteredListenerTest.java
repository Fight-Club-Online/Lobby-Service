package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.UserRegisteredEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegisteredListenerTest {

    @Mock
    private CreateUserCharacterUseCase createUserCharacterUseCase;

    @InjectMocks
    private UserRegisteredListener listener;

    @Test
    void testRecibirEventoCreatesCharacters() {
        // Arrange
        UserRegisteredEvent event = new UserRegisteredEvent();
        event.setUserId("user123");
        event.setUsername("testuser");

        UserCharacter character = UserCharacter.builder()
                .userId("user123")
                .characterName("samurai")
                .build();

        when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                .thenReturn(character);

        // Act
        listener.recibirEvento(event);

        // Assert
        ArgumentCaptor<CreateUserCharacterDTO> captor = ArgumentCaptor.forClass(CreateUserCharacterDTO.class);
        verify(createUserCharacterUseCase, times(5)).createUserCharacter(captor.capture());
    }

    @Test
    void testRecibirEventoHandlesException() {
        // Arrange
        UserRegisteredEvent event = new UserRegisteredEvent();
        event.setUserId("user456");
        event.setUsername("testuser2");

        when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                                .thenThrow(new RuntimeException("Database error 1"))
                                .thenThrow(new RuntimeException("Database error 2"))
                                .thenThrow(new RuntimeException("Database error 3"))
                                .thenThrow(new RuntimeException("Database error 4"))
                                .thenThrow(new RuntimeException("Database error 5"));

        // Act & Assert - Should not throw exception
        assertThatNoException().isThrownBy(() -> listener.recibirEvento(event));
        verify(createUserCharacterUseCase, times(5)).createUserCharacter(any(CreateUserCharacterDTO.class));
    }

    @Test
    void testRecibirEventoCreatesCorrectCharacterTypes() {
        // Arrange
        UserRegisteredEvent event = new UserRegisteredEvent();
        event.setUserId("user789");
        event.setUsername("newuser");

        when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                .thenReturn(UserCharacter.builder().build());

        // Act
        listener.recibirEvento(event);

        // Assert
        ArgumentCaptor<CreateUserCharacterDTO> captor = ArgumentCaptor.forClass(CreateUserCharacterDTO.class);
        verify(createUserCharacterUseCase, times(5)).createUserCharacter(captor.capture());

        // Verify character names
        var capturedDTOs = captor.getAllValues();
        assertThat(capturedDTOs).extracting("characterName")
                .contains("samurai", "demonio", "caballero", "golem", "esqueleto");
    }

    @Test
    void testRecibirEventoCreatesCorrectStats() {
        // Arrange
        UserRegisteredEvent event = new UserRegisteredEvent();
        event.setUserId("user999");
        event.setUsername("player");

        when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                .thenReturn(UserCharacter.builder().build());

        // Act
        listener.recibirEvento(event);

        // Assert
        ArgumentCaptor<CreateUserCharacterDTO> captor = ArgumentCaptor.forClass(CreateUserCharacterDTO.class);
        verify(createUserCharacterUseCase, times(5)).createUserCharacter(captor.capture());
        
        var capturedDTOs = captor.getAllValues();
        // Verify samurai stats
        var samuraiDTO = capturedDTOs.stream()
                .filter(dto -> "samurai".equals(dto.getCharacterName()))
                .findFirst();
        assertThat(samuraiDTO).isPresent();
        assertThat(samuraiDTO.get().getCharacterHp()).isEqualTo(105);
        assertThat(samuraiDTO.get().getCharacterATK()).isEqualTo(10);
    }

    @Test
    void testRecibirEventoWithDifferentUsers() {
        // Arrange
        String[] userIds = {"user1", "user2", "user3"};

        for (String userId : userIds) {
            UserRegisteredEvent event = new UserRegisteredEvent();
            event.setUserId(userId);
            event.setUsername("user" + userId);

            when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                    .thenReturn(UserCharacter.builder().build());

            // Act
            listener.recibirEvento(event);
        }

                // Assert
                verify(createUserCharacterUseCase, times(15)).createUserCharacter(any(CreateUserCharacterDTO.class));
    }
}
