package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper;

import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.CharacterAssetsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterAssetsMapper {

    CharacterAssets toDomain(CharacterAssetsEntity entity);

    CharacterAssetsEntity toEntity(CharacterAssets domain);
}
