package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest;

import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Player.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private long roomId;
    private String roomCode;
    private String hostId;
    private String roomState;
    private int maxPlayers;
    private int currentPlayers;
    private int maxSpectators;
    private int currentSpectators;
    private List<PlayerDto> players;
}
