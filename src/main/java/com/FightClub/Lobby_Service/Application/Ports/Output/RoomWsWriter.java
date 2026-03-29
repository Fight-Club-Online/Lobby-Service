package com.FightClub.Lobby_Service.Application.Ports.Output;


import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface RoomWsWriter {
    void joinRoom(String roomId, Room room);
    void leaveRoom(String roomId,String guestId);
}
