package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSocketRequestDTO {
    private String roomCode;
    private String userId;
    private String playerType;
}
