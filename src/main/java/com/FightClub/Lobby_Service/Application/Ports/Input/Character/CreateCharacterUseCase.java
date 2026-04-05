package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Application.DTO.CreateCharacterDTO;
import com.FightClub.Lobby_Service.Domain.Model.Character;

public interface CreateCharacterUseCase {
    Character createCharacter(CreateCharacterDTO dto);
}
