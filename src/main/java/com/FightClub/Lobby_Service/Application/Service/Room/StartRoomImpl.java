package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.StartRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SendMessageProducer;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartRoomImpl implements StartRoomUseCase {

    private final RoomCacheWriter roomCacheWriter;
    private final SendMessageProducer sendMessageProducer;

    @Override
    public Room startRoom(String codeRoom){
        Room r = roomCacheWriter.startRoom(codeRoom);
        sendMessageProducer.sendRoom(r);
        return r;
    }
}
