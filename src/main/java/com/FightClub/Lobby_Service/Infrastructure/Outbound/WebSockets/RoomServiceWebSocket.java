package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCreation;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RoomServiceWebSocket implements RoomCreation {
    @Override
    public Room createRoom(String hostId) {
        String roomCode = "ROOM-" + new Random().nextInt(1000) + hostId; ;
        return Room.builder().hostId(hostId).roomCode(roomCode).build();
    }
}
