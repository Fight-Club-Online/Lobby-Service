package com.FightClub.Lobby_Service.Application.Service;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Adapter.Inbound.Rest.DTO.Character.CharacterResponseDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.SeeUserCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SeeUserCharacterService implements SeeUserCharactersUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public List<UserCharacter> seeUserCharacters(String userId){
        return userCharacterRepository.findByUserID(userId);
    }

}
