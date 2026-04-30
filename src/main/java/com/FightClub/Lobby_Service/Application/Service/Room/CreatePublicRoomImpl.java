package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePublicRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Player;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CreatePublicRoomImpl implements CreatePublicRoomUseCase {

    private final RoomCacheWriter roomCacheWriter;

    @Override
    public Room createPrivateRoom(String hostId) {
        String roomCode = "ROOM-" + new Random().nextInt(1000) + hostId;
        Random random = new Random();
        long roomId = random.nextLong();
        Player player = Player.builder().userId(hostId).roomCode(roomCode).playerType(PlayerType.PLAYER).build();

        Room r = Room.builder().hostId(hostId)
                .roomId(roomId)
                .roomCode(roomCode)
                .roomState(RoomState.WAITING)
                .maxPlayers(2)
                .maxSpectators(5)
                .currentPlayers(1)
                .currentSpectators(0)
                .players(java.util.List.of(player))
                .isPublic(true)
                .build();
        roomCacheWriter.saveRoom(r);
        return r;
    }
}
