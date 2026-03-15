package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Adapter;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.UserCharacterMapper;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository.UserCharacterMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserCharacterRepositoryAdapter implements UserCharacterRepository {

    private final UserCharacterMongoRepository userCharacterMongoRepository;
    private final UserCharacterMapper userCharacterMapper;

    @Override
    public List<UserCharacter> findByUserID(String userId) {
        return userCharacterMongoRepository.findByUser(userId)
                .stream()
                .map(userCharacterMapper::toDomain)
                .toList();
    }
    @Override
    public UserCharacter findByCharacterID(Long characterID) {
        return userCharacterMapper.toDomain(
                userCharacterMongoRepository.findByCharacterCharacterId(characterID)
        );
    }

    @Override
    public UserCharacter save(UserCharacter userCharacter) {

        UserCharacterEntity entity = userCharacterMapper.toEntity(userCharacter);

        UserCharacterEntity savedEntity = userCharacterMongoRepository.save(entity);

        return userCharacterMapper.toDomain(savedEntity);
    }
    @Override
    public UserCharacter findByCharacterName(String characterName) {
        return userCharacterMapper.toDomain(
                userCharacterMongoRepository.findByCharacterCharacterName(characterName)
        );
    }
}
