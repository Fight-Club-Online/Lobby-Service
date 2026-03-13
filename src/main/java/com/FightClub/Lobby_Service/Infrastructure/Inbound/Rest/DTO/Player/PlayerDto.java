package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Player;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String userId;
    private String roomCode;
    private PlayerType playerType;
}
