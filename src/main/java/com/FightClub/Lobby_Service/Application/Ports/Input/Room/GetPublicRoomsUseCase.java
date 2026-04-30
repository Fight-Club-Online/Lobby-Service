package com.FightClub.Lobby_Service.Application.Ports.Input.Room;

import com.FightClub.Lobby_Service.Domain.Model.Room;

import java.util.List;

public interface GetPublicRoomsUseCase {
    List<Room> getPublicRooms();
}
