package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;

public interface CreateUserCharacterUseCase {
    UserCharacter createUserCharacter(CreateUserCharacterDTO dto);
}
