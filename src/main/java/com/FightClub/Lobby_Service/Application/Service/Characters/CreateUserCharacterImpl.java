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
                .characterId(dto.getCharacterId())      
                .characterName(dto.getCharacterName())  
                .characterLevel(dto.getCharacterLevel())
                .characterHp(dto.getCharacterHp())      
                .characterATK(dto.getCharacterATK())    
                .characterDEF(dto.getCharacterDEF())    
                .userId(dto.getUserId())                
                .build();

        return userCharacterRepository.save(userCharacter);
    }
}
