package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.SaveRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CreatePrivateRoomImpl implements CreatePrivateRoomUseCase {

    private final SaveRoomCache saveRoomCache;

    @Override
    public Room createPrivateRoom(String hostId) {
        String roomCode = "ROOM-" + new Random().nextInt(1000) + hostId;
        Random random = new Random();
        long roomId = random.nextLong();
        Room r = Room.builder().hostId(hostId)
                .roomId(roomId)
                .roomCode(roomCode)
                .roomState(RoomState.WAITING)
                .maxPlayers(2)
                .maxSpectators(5)
                .currentPlayers(1)
                .currentSpectators(0)
                .build();
        saveRoomCache.saveRoom(r);
        return r;
    }
}
