package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.CreateCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateCharacterUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Character;
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
class CharacterControllerTest {

    @Mock
    private CreateCharacterUseCase createCharacterUseCase;

    @Mock
    private SeeCharactersUseCase seeCharactersUseCase;

    @InjectMocks
    private CharacterController controller;

    @Test
    void testSeeAllCharactersSuccess() {
        // Arrange
        List<Character> expectedCharacters = Arrays.asList(
            Character.builder().characterId(1L).characterName("samurai").build(),
            Character.builder().characterId(2L).characterName("demonio").build(),
            Character.builder().characterId(3L).characterName("caballero").build()
        );

        when(seeCharactersUseCase.seeAllCharacters()).thenReturn(expectedCharacters);

        // Act
        ResponseEntity<List<Character>> response = controller.seeAllCharacters();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3);
        assertThat(response.getBody().get(0).getCharacterName()).isEqualTo("samurai");
        verify(seeCharactersUseCase, times(1)).seeAllCharacters();
    }

    @Test
    void testSeeAllCharactersEmpty() {
        // Arrange
        when(seeCharactersUseCase.seeAllCharacters()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Character>> response = controller.seeAllCharacters();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void testCreateCharacterSuccess() {
        // Arrange
        CreateCharacterDTO dto = CreateCharacterDTO.builder()
                .characterName("samurai")
            .characterHp("105")
            .characterATK("20")
            .characterDEF("5")
                .build();

        Character expectedCharacter = Character.builder()
            .characterId(456L)
                .characterName("samurai")
            .characterHp("105")
            .characterATK("20")
            .characterDEF("5")
                .build();

        when(createCharacterUseCase.createCharacter(dto)).thenReturn(expectedCharacter);

        // Act
        ResponseEntity<Character> response = controller.createCharacter(dto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCharacterName()).isEqualTo("samurai");
        assertThat(response.getBody().getCharacterHp()).isEqualTo("105");
        verify(createCharacterUseCase, times(1)).createCharacter(dto);
    }

    @Test
    void testCreateMultipleCharacters() {
        // Arrange
        String[] characterNames = {"samurai", "demonio", "caballero"};

        for (String name : characterNames) {
            CreateCharacterDTO dto = CreateCharacterDTO.builder()
                    .characterName(name)
                    .build();

            Character expectedCharacter = Character.builder()
                    .characterId((long) (name.hashCode() & Integer.MAX_VALUE))
                    .characterName(name)
                    .build();

            when(createCharacterUseCase.createCharacter(dto)).thenReturn(expectedCharacter);

            // Act
            ResponseEntity<Character> response = controller.createCharacter(dto);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(response.getBody().getCharacterName()).isEqualTo(name);
        }
    }

    @Test
    void testSeeAllCharactersWithMultipleCharacters() {
        // Arrange
        List<Character> expectedCharacters = Arrays.asList(
                Character.builder().characterName("samurai").build(),
                Character.builder().characterName("demonio").build(),
                Character.builder().characterName("caballero").build(),
                Character.builder().characterName("golem").build(),
                Character.builder().characterName("esqueleto").build()
        );

        when(seeCharactersUseCase.seeAllCharacters()).thenReturn(expectedCharacters);

        // Act
        ResponseEntity<List<Character>> response = controller.seeAllCharacters();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(5);
    }
}
