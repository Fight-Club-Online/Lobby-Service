package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.*;
import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCharacterControllerTest {

    @Mock
    private SeeUserCharactersUseCase seeUserCharactersUseCase;

    @Mock
    private CreateUserCharacterUseCase createUserCharacterUseCase;

    @Mock
    private DeleteUserCharacterUseCase deleteUserCharacterUseCase;

    @Mock
    private GetUserCharacterAssetsUseCase getUserCharacterAssetsUseCase;

    @Mock
    private GetUserCharacterUseCase getUserCharacterUseCase;

    @InjectMocks
    private UserCharacterController controller;

    @Test
    void testSeeUserCharactersSuccess() {
        // Arrange
        String userId = "user123";
        List<UserCharacter> expectedCharacters = Arrays.asList(
                UserCharacter.builder().characterId("char1").characterName("samurai").build(),
                UserCharacter.builder().characterId("char2").characterName("demonio").build()
        );

        when(seeUserCharactersUseCase.seeUserCharacters(userId)).thenReturn(expectedCharacters);

        // Act
        ResponseEntity<List<UserCharacter>> response = controller.seeUserCharacters(userId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        verify(seeUserCharactersUseCase, times(1)).seeUserCharacters(userId);
    }

    @Test
    void testSeeAllUserCharactersSuccess() {
        // Arrange
        List<UserCharacter> expectedCharacters = Arrays.asList(
                UserCharacter.builder().characterId("char1").characterName("samurai").build(),
                UserCharacter.builder().characterId("char2").characterName("demonio").build()
        );

        when(seeUserCharactersUseCase.seeAllUserCharacters()).thenReturn(expectedCharacters);

        // Act
        ResponseEntity<List<UserCharacter>> response = controller.seeAllUserCharacters();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        verify(seeUserCharactersUseCase, times(1)).seeAllUserCharacters();
    }

    @Test
    void testCreateUserCharacterSuccess() {
        // Arrange
        CreateUserCharacterDTO dto = CreateUserCharacterDTO.builder()
                .userId("user123")
                .characterId("char456")
                .characterName("samurai")
                .build();

        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId("user123")
                .characterId("char456")
                .characterName("samurai")
                .build();

        when(createUserCharacterUseCase.createUserCharacter(dto)).thenReturn(expectedCharacter);

        // Act
        ResponseEntity<UserCharacter> response = controller.createUserCharacter(dto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCharacterName()).isEqualTo("samurai");
        verify(createUserCharacterUseCase, times(1)).createUserCharacter(dto);
    }

    @Test
    void testDeleteUserCharacterSuccess() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";

        // Act
        ResponseEntity<Void> response = controller.deleteUserCharacter(userId, characterId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteUserCharacterUseCase, times(1)).deleteUserCharacter(userId, characterId);
    }

    @Test
    void testGetUserCharacterSuccess() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";
        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId(userId)
                .characterId(characterId)
                .characterName("samurai")
                .build();

        when(getUserCharacterUseCase.getUserCharacter(userId, characterId)).thenReturn(expectedCharacter);

        // Act
        ResponseEntity<UserCharacter> response = controller.getUserCharacter(userId, characterId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCharacterName()).isEqualTo("samurai");
    }

    @Test
    void testGetUserCharacterNotFound() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";

        when(getUserCharacterUseCase.getUserCharacter(userId, characterId)).thenReturn(null);

        // Act
        ResponseEntity<UserCharacter> response = controller.getUserCharacter(userId, characterId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testGetUserCharacterAssetsSuccess() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";
        CharacterAssets assets = CharacterAssets.builder()
                .idleUrl("idle.png")
                .runUrl("run.png")
                .build();

        when(getUserCharacterAssetsUseCase.getUserCharacterAssets(userId, characterId)).thenReturn(assets);

        // Act
        ResponseEntity<CharacterAssets> response = controller.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testGetUserCharacterAssetsNotFound() {
        // Arrange
        String userId = "user123";
        String characterId = "char456";

        when(getUserCharacterAssetsUseCase.getUserCharacterAssets(userId, characterId)).thenReturn(null);

        // Act
        ResponseEntity<CharacterAssets> response = controller.getUserCharacterAssets(userId, characterId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testSeeUserCharactersEmptyList() {
        // Arrange
        String userId = "user123";

        when(seeUserCharactersUseCase.seeUserCharacters(userId)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<UserCharacter>> response = controller.seeUserCharacters(userId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }
}
