package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper;

import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.UserCharacterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CharacterAssetsMapper.class})
public interface UserCharacterMapper {

    UserCharacter toDomain(UserCharacterEntity entity);

    UserCharacterEntity toEntity(UserCharacter domain);

}
