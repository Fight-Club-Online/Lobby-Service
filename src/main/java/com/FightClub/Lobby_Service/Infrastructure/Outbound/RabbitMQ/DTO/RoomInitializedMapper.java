package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO;


import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomInitializedMapper {
    RoomInitializedDTO toDto(Room room);
}
