package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket;

import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomStateMapperv2 {
    RoomStateDTO toDto(Room room);

    default String map(RoomState state) {
        return state != null ? state.name() : null;
    }
}
