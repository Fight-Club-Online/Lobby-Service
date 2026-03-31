package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository;

import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCharacterMongoRepository
        extends MongoRepository<UserCharacterEntity, String> {

    List<UserCharacterEntity> findByUser(String user);

    UserCharacterEntity findByCharacterCharacterId(Long characterId);

    UserCharacterEntity findByCharacterCharacterName(String characterName);
}