package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;


public interface JoinRoomWs {
    Room joinRoom(String roomId, String guestId);
}
