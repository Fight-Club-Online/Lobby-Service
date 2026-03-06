package com.FightClub.Lobby_Service.Domain.Repository;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;

import java.util.List;

public interface UserCharacterRepository {
    List<UserCharacter> findByUserID(String userId);
    UserCharacter findByCharacterID(Long characterID);
}
