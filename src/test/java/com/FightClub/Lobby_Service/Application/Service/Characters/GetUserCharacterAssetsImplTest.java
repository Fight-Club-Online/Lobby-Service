package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
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
class GetUserCharacterAssetsImplTest {

    @Mock
    private UserCharacterRepository userCharacterRepository;

    @InjectMocks
    private GetUserCharacterAssetsImpl getUserCharacterAssets;

    @Test
    void testGetUserCharacterAssetsWhenExists() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";
        CharacterAssets assets = CharacterAssets.builder()
                .idleUrl("http://localhost:8080/assets/character/IDLE.png")
                .runUrl("http://localhost:8080/assets/character/RUN.png")
                .attackUrl("http://localhost:8080/assets/character/ATTACK.png")
                .hurtUrl("http://localhost:8080/assets/character/HURT.png")
                .build();

        UserCharacter userCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .assets(assets)
                .build();

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(userCharacter);

        // Act
        CharacterAssets result = getUserCharacterAssets.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getIdleUrl()).isEqualTo("http://localhost:8080/assets/character/IDLE.png");
        assertThat(result.getRunUrl()).isEqualTo("http://localhost:8080/assets/character/RUN.png");
        verify(userCharacterRepository, times(1)).findByUserIdAndCharacterId(userId, characterId);
    }

    @Test
    void testGetUserCharacterAssetsWhenCharacterNotExists() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(null);

        // Act
        CharacterAssets result = getUserCharacterAssets.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(result).isNull();
        verify(userCharacterRepository, times(1)).findByUserIdAndCharacterId(userId, characterId);
    }

    @Test
    void testGetUserCharacterAssetsWhenAssetsAreNull() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";
        UserCharacter userCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .assets(null)
                .build();

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(userCharacter);

        // Act
        CharacterAssets result = getUserCharacterAssets.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void testGetUserCharacterAssetsWithCompleteAssets() {
        // Arrange
        String userId = "user789";
        String characterId = "char999";
        CharacterAssets assets = CharacterAssets.builder()
                .idleUrl("http://localhost/idle_samurai.png")
                .runUrl("http://localhost/run_samurai.png")
                .attackUrl("http://localhost/attack_samurai.png")
                .hurtUrl("http://localhost/hurt_samurai.png")
                .build();

        UserCharacter userCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .assets(assets)
                .build();

        when(userCharacterRepository.findByUserIdAndCharacterId(userId, characterId))
                .thenReturn(userCharacter);

        // Act
        CharacterAssets result = getUserCharacterAssets.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getAttackUrl()).isEqualTo("http://localhost/attack_samurai.png");
        assertThat(result.getHurtUrl()).isEqualTo("http://localhost/hurt_samurai.png");
    }
}
