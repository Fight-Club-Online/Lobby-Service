package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;

import java.util.List;

public interface SeeUserCharactersUseCase {
    List<UserCharacter> seeUserCharacters(String userId);
    List<UserCharacter> seeAllUserCharacters();
}
