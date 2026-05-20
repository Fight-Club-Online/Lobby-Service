package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.GetUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserCharacterImplTest {

    @Mock
    private UserCharacterRepository userCharacterRepository;

    @InjectMocks
    private GetUserCharacterImpl getUserCharacter;

    @Test
    void testGetUserCharacterWhenExists() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";
        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .characterName("samurai")
                .characterLevel(1)
                .build();

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(expectedCharacter);

        // Act
        UserCharacter result = getUserCharacter.getUserCharacter(userId, characterId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getCharacterId()).isEqualTo(characterId);
        assertThat(result.getCharacterName()).isEqualTo("samurai");
        verify(userCharacterRepository, times(1)).findByUserIdAndCharacterId(userId, characterId);
    }

    @Test
    void testGetUserCharacterWhenNotExists() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(null);

        // Act
        UserCharacter result = getUserCharacter.getUserCharacter(userId, characterId);

        // Assert
        assertThat(result).isNull();
        verify(userCharacterRepository, times(1)).findByUserIdAndCharacterId(userId, characterId);
    }

    @Test
    void testGetUserCharacterWithDifferentIds() {
        // Arrange
        String userId = "user789";
        String characterId = "char999";
        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .characterName("demonio")
                .characterLevel(5)
                .build();

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(expectedCharacter);

        // Act
        UserCharacter result = getUserCharacter.getUserCharacter(userId, characterId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCharacterName()).isEqualTo("demonio");
        assertThat(result.getCharacterLevel()).isEqualTo(5);
    }
}
