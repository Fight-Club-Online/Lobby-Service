package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO;

import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class RoomInitializedDTO {
    private long roomId;
    private String roomCode;
    private RoomState roomState;
    private String hostId;
    private int maxPlayers;
    private int currentPlayers;
    private int maxSpectators;
    private int currentSpectators;
    private List<PlayerEventDTO> players ;
}
