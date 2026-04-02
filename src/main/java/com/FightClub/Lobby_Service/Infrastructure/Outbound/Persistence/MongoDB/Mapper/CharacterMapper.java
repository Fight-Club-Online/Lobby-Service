package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper;

import com.FightClub.Lobby_Service.Domain.Model.Character;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    Character toDomain(CharacterEntity entity);

    CharacterEntity toEntity(Character domain);

}
