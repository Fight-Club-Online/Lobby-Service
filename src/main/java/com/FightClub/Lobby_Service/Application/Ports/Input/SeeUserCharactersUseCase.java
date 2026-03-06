package com.FightClub.Lobby_Service.Application.Ports.Input;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Adapter.Inbound.Rest.DTO.Character.CharacterResponseDTO;

import java.util.List;

public interface SeeUserCharactersUseCase {
    List<UserCharacter> seeUserCharacters(String userId);
}
