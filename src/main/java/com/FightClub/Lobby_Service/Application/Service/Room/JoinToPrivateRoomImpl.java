package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.JoinRoomWs;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinToPrivateRoomImpl implements JoinToPrivateRoomUseCase {
    private final JoinRoomWs joinRoomWs;
    private final SearchRoomCache searchRoomCache;

    @Override
    public Room joinToPrivateRoom(JoinRoomCommandDTO joinRoomCommandDTO) {
        if(!searchRoomCache.searchRoom(joinRoomCommandDTO.roomCode())){
            throw new RuntimeException("Room not found");
        }
        return joinRoomWs.joinRoom(joinRoomCommandDTO.roomCode(), joinRoomCommandDTO.guestId());
    }
}
