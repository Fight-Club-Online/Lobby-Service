package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository;

import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserCharacterMongoRepository
        extends MongoRepository<UserCharacterEntity, Long> {

    List<UserCharacterEntity> findByUserId(String userId);

    Optional<UserCharacterEntity> findByUserIdAndCharacterId(String userId, String characterId);

    void deleteByUserIdAndCharacterId(String userId, String characterId);

    void deleteByUserId(String userId);
}