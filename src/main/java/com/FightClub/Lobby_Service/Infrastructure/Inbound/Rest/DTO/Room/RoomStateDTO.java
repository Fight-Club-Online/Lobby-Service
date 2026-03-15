package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomStateDTO {
    private String roomCode;
    private String hostId;
    private String roomState;
    //private List<PlayerDTO> players;
    private int maxPlayers;
}
