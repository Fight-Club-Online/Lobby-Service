package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Domain.Model.Character;
import com.FightClub.Lobby_Service.Domain.Repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeeCharactersImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private SeeCharactersImpl seeCharacters;

    @Test
    void testSeeAllCharactersWhenListIsNotEmpty() {
        // Arrange
        Character character1 = Character.builder()
            .characterId(1L)
                .characterName("samurai")
            .characterHp("105")
            .characterATK("20")
                .build();

        Character character2 = Character.builder()
            .characterId(2L)
                .characterName("demonio")
            .characterHp("95")
            .characterATK("15")
                .build();

        List<Character> expectedCharacters = Arrays.asList(character1, character2);

        when(characterRepository.findAllCharacters()).thenReturn(expectedCharacters);

        // Act
        List<Character> result = seeCharacters.seeAllCharacters();

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).contains(character1, character2);
        verify(characterRepository, times(1)).findAllCharacters();
    }

    @Test
    void testSeeAllCharactersWhenListIsEmpty() {
        // Arrange
        when(characterRepository.findAllCharacters()).thenReturn(Collections.emptyList());

        // Act
        List<Character> result = seeCharacters.seeAllCharacters();

        // Assert
        assertThat(result).isEmpty();
        verify(characterRepository, times(1)).findAllCharacters();
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

        when(characterRepository.findAllCharacters()).thenReturn(expectedCharacters);

        // Act
        List<Character> result = seeCharacters.seeAllCharacters();

        // Assert
        assertThat(result).hasSize(5);
        assertThat(result.get(0).getCharacterName()).isEqualTo("samurai");
        assertThat(result.get(4).getCharacterName()).isEqualTo("esqueleto");
    }

    @Test
    void testSeeAllCharactersVerifiesRepositoryCall() {
        // Arrange
        when(characterRepository.findAllCharacters()).thenReturn(Collections.emptyList());

        // Act
        seeCharacters.seeAllCharacters();

        // Assert
        verify(characterRepository, times(1)).findAllCharacters();
        verifyNoMoreInteractions(characterRepository);
    }

    @Test
    void testSeeAllCharactersReturnsSameInstanceFromRepository() {
        // Arrange
        Character character = Character.builder()
                .characterId(1L)
                .characterName("samurai")
                .build();
        List<Character> expectedCharacters = Collections.singletonList(character);

        when(characterRepository.findAllCharacters()).thenReturn(expectedCharacters);

        // Act
        List<Character> result = seeCharacters.seeAllCharacters();

        // Assert
        assertThat(result).isSameAs(expectedCharacters);
    }
}
