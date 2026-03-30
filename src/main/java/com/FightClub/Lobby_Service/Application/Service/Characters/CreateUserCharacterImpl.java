package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Character;
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
        Character character = Character.builder()
                .characterId(dto.getCharacterId())
                .characterLevel(dto.getCharacterLevel())
                .characterName(dto.getCharacterName())
                .characterHp(dto.getCharacterHp())
                .characterATK(dto.getCharacterATK())
                .build();

        UserCharacter userCharacter = UserCharacter.builder()
                .character(character)
                .user(dto.getUserId())
                .build();

        return userCharacterRepository.save(userCharacter);
    }
}
