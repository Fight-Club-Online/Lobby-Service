package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.DTO.CreateCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Character;
import com.FightClub.Lobby_Service.Domain.Repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCharacterImpl implements CreateCharacterUseCase {

    private final CharacterRepository characterRepository;

    @Override
    public Character createCharacter(CreateCharacterDTO dto) {
        Character character = Character.builder()
                .characterId(dto.getCharacterId())
                .characterLevel(dto.getCharacterLevel())
                .characterName(dto.getCharacterName())
                .characterHp(dto.getCharacterHp())
                .characterATK(dto.getCharacterATK())
                .characterDEF(dto.getCharacterDEF())
                .build();

        return characterRepository.save(character);
    }
}
