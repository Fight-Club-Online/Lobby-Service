package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Adapter;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.UserCharacterMapper;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository.UserCharacterMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCharacterRepositoryAdapter implements UserCharacterRepository {

    private final UserCharacterMongoRepository userCharacterMongoRepository;
    private final UserCharacterMapper userCharacterMapper;
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @Override
    public List<UserCharacter> findByUserID(String userId) {
        return userCharacterMongoRepository.findByUserId(userId)
                .stream()
                .map(userCharacterMapper::toDomain)
                .peek(this::enrichWithAssets) 
                .toList();
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
        // Busca por characterName directamente (ahora es atributo de UserCharacter)
        return userCharacterMongoRepository.findAll()
                .stream()
                .filter(uc -> uc.getCharacterName().equals(characterName))
                .findFirst()
                .map(userCharacterMapper::toDomain)
                .orElse(null);
    }
    
    @Override
    public void deleteByUserIdAndCharacterId(String userId, Long characterId) {
        userCharacterMongoRepository.deleteByUserIdAndCharacterId(userId, characterId);
    }
    
    @Override
    public UserCharacter findByUserIdAndCharacterId(String userId, Long characterId) {
        UserCharacterEntity entity = userCharacterMongoRepository.findByUserIdAndCharacterId(userId, characterId).orElse(null);
        if (entity == null) return null;
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

