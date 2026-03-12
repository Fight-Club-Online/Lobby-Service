package com.FightClub.Lobby_Service.Application.Ports.Output;

public interface SearchRoomCache {
    Boolean searchRoomByCode(String roomCode);
    Boolean searchRoomById(long roomId);
    String getRoomCode(long roomId);
    int getRoomAvailability(String roomCode);
}
