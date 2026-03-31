package com.FightClub.Lobby_Service.Application.Ports.Input.Room;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface GetRoomAvailabilityUseCase {
    Room getRoomAvailability(String roomCode);
}
