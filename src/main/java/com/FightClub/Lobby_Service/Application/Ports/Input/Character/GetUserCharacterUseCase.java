package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;

public interface GetUserCharacterUseCase {
    UserCharacter getUserCharacter(String userId, String characterId);
}
