package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserCharacterImplTest {

    @Mock
    private UserCharacterRepository userCharacterRepository;

    @InjectMocks
    private CreateUserCharacterImpl createUserCharacter;

    @Test
    void testCreateUserCharacterSuccessfully() {
        // Arrange
        CreateUserCharacterDTO dto = CreateUserCharacterDTO.builder()
                .userId("user123")
                .characterId("char456")
                .characterName("samurai")
                .characterLevel(1)
                .characterHp(105)
                .characterATK(20)
                .characterDEF(5)
                .build();

        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId(dto.getUserId())
                .characterId(dto.getCharacterId())
                .characterName(dto.getCharacterName())
                .characterLevel(dto.getCharacterLevel())
                .characterHp(dto.getCharacterHp())
                .characterATK(dto.getCharacterATK())
                .characterDEF(dto.getCharacterDEF())
                .build();

        when(userCharacterRepository.save(any(UserCharacter.class)))
                .thenReturn(expectedCharacter);

        // Act
        UserCharacter result = createUserCharacter.createUserCharacter(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo("user123");
        assertThat(result.getCharacterName()).isEqualTo("samurai");
        assertThat(result.getCharacterLevel()).isEqualTo(1);
        assertThat(result.getCharacterHp()).isEqualTo(105);
        assertThat(result.getCharacterATK()).isEqualTo(20);
        assertThat(result.getCharacterDEF()).isEqualTo(5);

        verify(userCharacterRepository, times(1)).save(any(UserCharacter.class));
    }

    @Test
    void testCreateUserCharacterWithDifferentStats() {
        // Arrange
        CreateUserCharacterDTO dto = CreateUserCharacterDTO.builder()
                .userId("user789")
                .characterId("char999")
                .characterName("demonio")
                .characterLevel(5)
                .characterHp(95)
                .characterATK(15)
                .characterDEF(8)
                .build();

        UserCharacter expectedCharacter = UserCharacter.builder()
                .userId(dto.getUserId())
                .characterId(dto.getCharacterId())
                .characterName(dto.getCharacterName())
                .characterLevel(dto.getCharacterLevel())
                .characterHp(dto.getCharacterHp())
                .characterATK(dto.getCharacterATK())
                .characterDEF(dto.getCharacterDEF())
                .build();

        when(userCharacterRepository.save(any(UserCharacter.class)))
                .thenReturn(expectedCharacter);

        // Act
        UserCharacter result = createUserCharacter.createUserCharacter(dto);

        // Assert
        assertThat(result.getCharacterName()).isEqualTo("demonio");
        assertThat(result.getCharacterLevel()).isEqualTo(5);
        assertThat(result.getCharacterDEF()).isEqualTo(8);
    }

    @Test
    void testCreateUserCharacterVerifiesCorrectParameters() {
        // Arrange
        CreateUserCharacterDTO dto = CreateUserCharacterDTO.builder()
                .userId("user123")
                .characterId("char456")
                .characterName("caballero")
                .characterLevel(1)
                .characterHp(110)
                .characterATK(20)
                .characterDEF(5)
                .build();

        UserCharacter savedCharacter = UserCharacter.builder()
                .userId(dto.getUserId())
                .characterId(dto.getCharacterId())
                .characterName(dto.getCharacterName())
                .characterLevel(dto.getCharacterLevel())
                .characterHp(dto.getCharacterHp())
                .characterATK(dto.getCharacterATK())
                .characterDEF(dto.getCharacterDEF())
                .build();

        when(userCharacterRepository.save(any(UserCharacter.class)))
                .thenReturn(savedCharacter);

        // Act
        UserCharacter result = createUserCharacter.createUserCharacter(dto);

        // Assert
        ArgumentCaptor<UserCharacter> captor = ArgumentCaptor.forClass(UserCharacter.class);
        verify(userCharacterRepository).save(captor.capture());
        
        UserCharacter capturedCharacter = captor.getValue();
        assertThat(capturedCharacter.getUserId()).isEqualTo("user123");
        assertThat(capturedCharacter.getCharacterId()).isEqualTo("char456");
    }

    @Test
    void testCreateMultipleUserCharactersWithDifferentValues() {
        // Arrange & Act & Assert
        for (int i = 0; i < 3; i++) {
            CreateUserCharacterDTO dto = CreateUserCharacterDTO.builder()
                    .userId("user" + i)
                    .characterId("char" + i)
                    .characterName("character" + i)
                    .characterLevel(i + 1)
                    .characterHp(100 + i * 5)
                    .characterATK(20 + i)
                    .characterDEF(5 + i)
                    .build();

            UserCharacter expectedCharacter = UserCharacter.builder()
                    .userId(dto.getUserId())
                    .characterId(dto.getCharacterId())
                    .characterName(dto.getCharacterName())
                    .characterLevel(dto.getCharacterLevel())
                    .characterHp(dto.getCharacterHp())
                    .characterATK(dto.getCharacterATK())
                    .characterDEF(dto.getCharacterDEF())
                    .build();

            when(userCharacterRepository.save(any(UserCharacter.class)))
                    .thenReturn(expectedCharacter);

            UserCharacter result = createUserCharacter.createUserCharacter(dto);
            assertThat(result.getUserId()).isEqualTo("user" + i);
        }
    }
}
