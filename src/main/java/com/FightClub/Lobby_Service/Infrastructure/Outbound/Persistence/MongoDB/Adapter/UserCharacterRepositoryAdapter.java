package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Adapter;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository.UserCharacterMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserCharacterRepositoryAdapter implements UserCharacterRepository {

    private final UserCharacterMongoRepository userCharacterMongoRepository;

    @Override
    public List<UserCharacter> findByUserID(String userId) {
        return userCharacterMongoRepository.findByUserID(userId);
    }

    @Override
    public UserCharacter findByCharacterID(Long characterID) {
        return userCharacterMongoRepository.findByCharacterID(characterID);
    }

    @Override
    public UserCharacter save(UserCharacter userCharacter) {
        return userCharacterMongoRepository.save(userCharacter);
    }

    @Override
    public UserCharacter findByCharacterName(String characterName) {
        return userCharacterMongoRepository.findByCharacterName(characterName);
    }
}
