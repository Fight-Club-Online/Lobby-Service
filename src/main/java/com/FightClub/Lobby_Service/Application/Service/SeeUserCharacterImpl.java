package com.FightClub.Lobby_Service.Application.Service;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeUserCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SeeUserCharacterImpl implements SeeUserCharactersUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public List<UserCharacter> seeUserCharacters(String userId){
        return userCharacterRepository.findByUserID(userId);
    }

}
