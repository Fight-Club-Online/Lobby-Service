package com.FightClub.Lobby_Service.Domain.Model;

import com.FightClub.Lobby_Service.Domain.Model.Enums.roomState;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private long roomId;
    private String roomCode;
    private roomState roomState;
    private String hostId;
    private int maxPlayers;
    private int currentPlayers;
    private int maxSpectators;
    private int currentSpectators;
    private List<Player> players = new ArrayList<>();
}
