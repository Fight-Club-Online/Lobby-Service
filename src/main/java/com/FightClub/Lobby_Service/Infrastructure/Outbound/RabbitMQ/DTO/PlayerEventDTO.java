package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlayerEventDTO {
    private String userId;
    private String roomCode;
    private PlayerType playerType;
}
