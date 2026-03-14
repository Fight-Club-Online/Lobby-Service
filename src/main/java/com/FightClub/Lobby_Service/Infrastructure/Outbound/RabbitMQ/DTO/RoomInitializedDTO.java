package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO;

import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Player;

import java.util.ArrayList;
import java.util.List;

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
