package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface RoomCacheWriter {
    void saveRoom(Room room);
    void addPlayerToRoomById(String userId, long roomId);
    void addPlayerToRoomByCode(String userId, String roomCode);
}
