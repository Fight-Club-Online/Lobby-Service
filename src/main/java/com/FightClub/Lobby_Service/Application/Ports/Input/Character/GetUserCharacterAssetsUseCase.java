package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;

public interface GetUserCharacterAssetsUseCase {
    CharacterAssets getUserCharacterAssets(String characterId);
}
