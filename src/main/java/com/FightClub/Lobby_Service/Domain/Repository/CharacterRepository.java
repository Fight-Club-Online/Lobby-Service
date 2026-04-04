package com.FightClub.Lobby_Service.Domain.Repository;

import com.FightClub.Lobby_Service.Domain.Model.Character;

public interface CharacterRepository {
    Character save(Character character);
    Character findByCharacterId(String characterId);
    void deleteByCharacterId(String characterId);
}
