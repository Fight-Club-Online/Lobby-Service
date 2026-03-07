package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface RoomCreation {
    Room createRoom(String hostId);
}
