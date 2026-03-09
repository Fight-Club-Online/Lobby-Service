package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface SaveRoomCache {
    void saveRoom(Room room);
}
