package com.FightClub.Lobby_Service.Domain.Repository;

import com.FightClub.Lobby_Service.Domain.Model.Character;

import java.util.List;

public interface CharacterRepository {
    List<Character> findAllCharacters();
    Character save(Character character);
    Character findByCharacterId(String characterId);
    void deleteByCharacterId(String characterId);
}
