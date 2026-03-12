package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface PlayerCacheWriter {
    Room leaveRoom(String userId);
    Room addPlayerToRoom(String userId, long roomId);
    Room addPlayerToRoomByRole(String userId, long roomId, PlayerType role);
}
