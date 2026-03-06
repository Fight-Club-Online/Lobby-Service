package com.FightClub.Lobby_Service.Application.Ports.Input.Room;

public interface JoinToPrivateRoomUseCase {
    void joinToPrivateRoom(String roomId, String guestId);
}
