package com.FightClub.Lobby_Service.Domain;

import com.FightClub.Lobby_Service.Domain.Enums.playerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private String userId;
    private String roomCode;
    private playerType playerType;
    private long characterId;
}
