package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.StartRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartRoomImpl implements StartRoomUseCase {

    private final RoomCacheWriter roomCacheWriter;

    @Override
    public Room startRoom(String codeRoom){
        return roomCacheWriter.startRoom(codeRoom);
    }
}
