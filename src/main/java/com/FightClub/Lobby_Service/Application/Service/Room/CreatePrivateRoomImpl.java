package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCreation;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePrivateRoomImpl implements CreatePrivateRoomUseCase {
    private final RoomCreation roomCreation;

    @Override
    public Room createPrivateRoom(String hostId) {
        return roomCreation.createRoom(hostId);
    }
}
