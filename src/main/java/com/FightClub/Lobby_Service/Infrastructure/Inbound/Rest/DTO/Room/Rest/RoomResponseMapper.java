package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomResponseMapper {
    Room toClass (RoomResponseDTO roomResponseDTO);
    RoomResponseDTO toDTO (Room room);
}
