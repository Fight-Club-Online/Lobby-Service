package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room;

import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomStateMapper {
    RoomStateDTO toDto(Room room);

    default String map(RoomState state) {
        return state != null ? state.name() : null;
    }
}
