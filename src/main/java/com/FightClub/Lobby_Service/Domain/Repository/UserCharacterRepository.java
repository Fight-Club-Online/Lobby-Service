package com.FightClub.Lobby_Service.Domain.Repository;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;

import java.util.List;

public interface UserCharacterRepository {
    List<UserCharacter> findByUserID(String userId);
    UserCharacter save(UserCharacter userCharacter);
    UserCharacter findByCharacterName(String characterName);
    void deleteByCharacterID(String characterID);
    UserCharacter findByID(String id);
}
