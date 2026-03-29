package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room;

import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Player.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomStateDTO {
    private String roomCode;
    private String hostId;
    private String roomState;
    private List<PlayerDto> players;
    private int maxPlayers;
    private int currentPlayers;
    private int currentSpectators;
    private int maxSpectators;
    private long roomId;
}
