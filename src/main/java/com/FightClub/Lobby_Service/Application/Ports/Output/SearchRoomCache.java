package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;

import java.util.List;

public interface SearchRoomCache {
    Boolean searchRoomByCode(String roomCode);
    Boolean searchRoomById(long roomId);
    String getRoomCode(long roomId);
    int getRoomAvailability(String roomCode);
    Room getRoomById(long roomId);
    Room getRoomByCode(String roomCode);
    List<Room> getPublicRooms();
}
