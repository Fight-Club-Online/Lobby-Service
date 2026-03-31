package com.FightClub.Lobby_Service.Application.DTO;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;

public record JoinRoomPTCommandDTO(String roomCode, String guestId, PlayerType playerType)  {
}
