package com.FightClub.Lobby_Service.Domain.Model;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private String userId;
    private String roomCode;
    private PlayerType playerType;
    private long characterId;
}

