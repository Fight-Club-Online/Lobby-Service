package com.FightClub.Lobby_Service.Application.Ports.Input.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface JoinAsSpectatorUseCase {
    Room joinAsSpectator(JoinRoomCommandDTO JoinRoomCommandDTO);
}
