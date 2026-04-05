package com.FightClub.Lobby_Service.Application.Ports.Input.Character;

public interface DeleteUserCharacterUseCase {
    void deleteUserCharacter(String userId, String characterId);
}
