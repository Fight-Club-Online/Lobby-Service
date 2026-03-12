package com.FightClub.Lobby_Service.Application.Ports.Output;


public interface RoomWsWriter {
    void joinRoom(String roomId, String guestId);
    void leaveRoom(String roomId,String guestId);
}
