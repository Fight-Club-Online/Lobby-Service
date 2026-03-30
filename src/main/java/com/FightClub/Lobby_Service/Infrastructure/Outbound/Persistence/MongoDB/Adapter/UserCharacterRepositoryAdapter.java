package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Adapter;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.UserCharacterMapper;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository.UserCharacterMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserCharacterRepositoryAdapter implements UserCharacterRepository {

    private final UserCharacterMongoRepository userCharacterMongoRepository;
    private final UserCharacterMapper userCharacterMapper;
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @Override
    public List<UserCharacter> findByUserID(String userId) {
        return userCharacterMongoRepository.findByUser(userId)
                .stream()
                .map(userCharacterMapper::toDomain)
                .peek(this::enrichWithAssets) 
                .toList();
    }
    
    @Override
    public UserCharacter findByCharacterID(Long characterID) {
        UserCharacterEntity entity = userCharacterMongoRepository.findByCharacterCharacterId(characterID);
        UserCharacter userCharacter = userCharacterMapper.toDomain(entity);
        enrichWithAssets(userCharacter); 
        return userCharacter;
    }

    @Override
    public UserCharacter save(UserCharacter userCharacter) {
        UserCharacterEntity entity = userCharacterMapper.toEntity(userCharacter);
        UserCharacterEntity savedEntity = userCharacterMongoRepository.save(entity);
        UserCharacter result = userCharacterMapper.toDomain(savedEntity);
        enrichWithAssets(result); 
        return result;
    }
    
    @Override
    public UserCharacter findByCharacterName(String characterName) {
        UserCharacterEntity entity = userCharacterMongoRepository.findByCharacterCharacterName(characterName);
        UserCharacter userCharacter = userCharacterMapper.toDomain(entity);
        enrichWithAssets(userCharacter);
        return userCharacter;
    }
    
    
    private void enrichWithAssets(UserCharacter userCharacter) {
        if (userCharacter != null && userCharacter.getAssets() == null) {
            userCharacter.setAssets(generateAssetUrls());
        }
    }
    
    
    private CharacterAssets generateAssetUrls() {
        return CharacterAssets.fromBaseUrl(baseUrl);
    }
}

