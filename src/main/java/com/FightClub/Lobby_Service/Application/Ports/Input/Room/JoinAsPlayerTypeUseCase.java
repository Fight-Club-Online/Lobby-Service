package com.FightClub.Lobby_Service.Application.Ports.Input.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomPTCommandDTO;
import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface JoinAsPlayerTypeUseCase {
    Room JoinAsPlayerType(JoinRoomPTCommandDTO joinRoomPTCommandDTO);
}
