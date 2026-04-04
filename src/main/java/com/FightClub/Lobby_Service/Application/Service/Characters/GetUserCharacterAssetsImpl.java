package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.GetUserCharacterAssetsUseCase;
import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetUserCharacterAssetsImpl implements GetUserCharacterAssetsUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public CharacterAssets getUserCharacterAssets(String userId, String characterId) {
        UserCharacter userCharacter = userCharacterRepository.findByUserIdAndCharacterId(userId, characterId);
        if (userCharacter != null && userCharacter.getAssets() != null) {
            return userCharacter.getAssets();
        }
        return null;
    }
}
