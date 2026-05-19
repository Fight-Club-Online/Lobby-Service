package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.GetUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetUserCharacterImpl implements GetUserCharacterUseCase {

    private final UserCharacterRepository userCharacterRepository;

    @Override
    public UserCharacter getUserCharacter(String userId, String characterId) {
        log.info("Obteniendo character para userId: {} con characterId: {}", userId, characterId);
        UserCharacter userCharacter = userCharacterRepository.findByUserIdAndCharacterId(userId, characterId);
        
        if (userCharacter != null) {
            log.info("✅ Character encontrado: {}", userCharacter.getCharacterName());
        } else {
            log.warn("⚠️ Character no encontrado para userId: {} con characterId: {}", userId, characterId);
        }
        
        return userCharacter;
    }
}
