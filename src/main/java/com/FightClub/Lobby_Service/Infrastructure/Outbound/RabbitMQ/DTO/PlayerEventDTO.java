package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;

public class PlayerEventDTO {
    private String userId;
    private String roomCode;
    private PlayerType playerType;
}
