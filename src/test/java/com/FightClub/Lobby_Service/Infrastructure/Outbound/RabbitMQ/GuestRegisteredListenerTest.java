package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.GuestRegisteredEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestRegisteredListenerTest {

    @Mock
    private CreateUserCharacterUseCase createUserCharacterUseCase;

    @InjectMocks
    private GuestRegisteredListener listener;

    @Test
    void testRecibirEventoCreatesCharacters() {
        // Arrange
        GuestRegisteredEvent event = new GuestRegisteredEvent();
        event.setUserId("guest123");

        UserCharacter character = UserCharacter.builder()
                .userId("guest123")
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
    void testRecibirEventoCreatesMultipleCharactersWithDifferentStats() {
        // Arrange
        GuestRegisteredEvent event = new GuestRegisteredEvent();
        event.setUserId("guest456");

        when(createUserCharacterUseCase.createUserCharacter(any(CreateUserCharacterDTO.class)))
                .thenReturn(UserCharacter.builder().build());

        // Act
        listener.recibirEvento(event);

        // Assert
        ArgumentCaptor<CreateUserCharacterDTO> captor = ArgumentCaptor.forClass(CreateUserCharacterDTO.class);
        verify(createUserCharacterUseCase, times(5)).createUserCharacter(captor.capture());
    }

    @Test
    void testRecibirEventoHandlesException() {
        // Arrange
        GuestRegisteredEvent event = new GuestRegisteredEvent();
        event.setUserId("guest789");

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
        GuestRegisteredEvent event = new GuestRegisteredEvent();
        event.setUserId("guest999");

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
}
