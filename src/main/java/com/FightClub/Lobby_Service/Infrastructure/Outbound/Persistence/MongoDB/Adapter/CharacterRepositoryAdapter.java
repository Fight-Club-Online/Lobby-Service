package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Adapter;

import com.FightClub.Lobby_Service.Domain.Model.Character;
import com.FightClub.Lobby_Service.Domain.Repository.CharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.CharacterEntity;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.CharacterMapper;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Repository.CharacterMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CharacterRepositoryAdapter implements CharacterRepository {

    private final CharacterMongoRepository characterMongoRepository;
    private final CharacterMapper characterMapper;

    @Override
    public Character save(Character character) {
        CharacterEntity entity = characterMapper.toEntity(character);
        CharacterEntity savedEntity = characterMongoRepository.save(entity);
        return characterMapper.toDomain(savedEntity);
    }

    @Override
    public Character findByCharacterId(long characterId) {
        return characterMongoRepository.findByCharacterId(characterId)
                .map(characterMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deleteByCharacterId(long characterId) {
        characterMongoRepository.deleteByCharacterId(characterId);
    }
}
