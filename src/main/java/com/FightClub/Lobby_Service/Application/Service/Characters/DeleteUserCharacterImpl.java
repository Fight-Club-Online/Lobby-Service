package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.DeleteUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserCharacterImpl implements DeleteUserCharacterUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public void deleteUserCharacter(String userId, Long characterId) {
        userCharacterRepository.deleteByUserIdAndCharacterId(userId, characterId);
    }
}
