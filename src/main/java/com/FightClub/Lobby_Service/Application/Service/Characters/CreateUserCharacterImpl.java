package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserCharacterImpl implements CreateUserCharacterUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public UserCharacter createUserCharacter(CreateUserCharacterDTO dto) {
        // Crear una copia independiente del Character con stats iniciales
        UserCharacter userCharacter = UserCharacter.builder()
                .characterId(dto.getCharacterId())      // Referencia al template
                .characterName(dto.getCharacterName())  // Copia del nombre
                .characterLevel(dto.getCharacterLevel())// Copia del nivel inicial
                .characterHp(dto.getCharacterHp())      // Copia del HP inicial
                .characterATK(dto.getCharacterATK())    // Copia del ATK inicial
                .characterDEF(dto.getCharacterDEF())    // Copia del DEF inicial
                .userId(dto.getUserId())                // Usuario propietario
                .build();

        return userCharacterRepository.save(userCharacter);
    }
}
