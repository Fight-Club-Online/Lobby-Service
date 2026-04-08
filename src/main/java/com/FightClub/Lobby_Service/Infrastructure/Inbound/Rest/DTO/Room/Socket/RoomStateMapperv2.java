package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket;

import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomStateMapperv2 {
    @Mapping(target = "roomId", expression = "java(String.valueOf(room.getRoomId()))")
    RoomStateDTOv2 toDto(Room room);

    default String map(RoomState state) {
        return state != null ? state.name() : null;
    }
}
