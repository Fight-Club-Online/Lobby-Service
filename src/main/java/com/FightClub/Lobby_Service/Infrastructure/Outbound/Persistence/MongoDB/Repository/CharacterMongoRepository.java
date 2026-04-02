package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository;

import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.CharacterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterMongoRepository
        extends MongoRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findByCharacterId(long characterId);

    void deleteByCharacterId(long characterId);
}
