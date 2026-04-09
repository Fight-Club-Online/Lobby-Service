package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Domain.Model.Character;

import java.util.List;

public interface SeeCharactersUseCase {
    List<Character> seeAllCharacters();
}
