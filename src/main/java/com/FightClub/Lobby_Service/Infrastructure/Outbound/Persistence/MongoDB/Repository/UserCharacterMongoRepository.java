package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCharacterMongoRepository extends MongoRepository <UserCharacterEntity, String>{
    List<UserCharacter> findByUserID(String userId);
    UserCharacter findByCharacterID(Long characterID);
    UserCharacter save(UserCharacter userCharacter);
    UserCharacter findByCharacterName(String characterName);
}
