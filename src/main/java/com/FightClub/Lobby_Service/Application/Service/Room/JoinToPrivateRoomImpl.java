package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.JoinRoomWs;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinToPrivateRoomImpl implements JoinToPrivateRoomUseCase {
    private final JoinRoomWs joinRoomWs;

    @Override
    public Room joinToPrivateRoom(JoinRoomCommandDTO joinRoomCommandDTO) {
        return joinRoomWs.joinRoom(joinRoomCommandDTO.roomCode(), joinRoomCommandDTO.guestId());
    }
}
