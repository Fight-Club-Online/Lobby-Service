package com.FightClub.Lobby_Service.Domain.Repository;

import com.FightClub.Lobby_Service.Domain.Model.Character;

public interface CharacterRepository {
    Character save(Character character);
    Character findByCharacterId(long characterId);
    void deleteByCharacterId(long characterId);
}
