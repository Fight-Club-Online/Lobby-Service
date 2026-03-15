package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;

import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JoinToPrivateRoomImpl implements JoinToPrivateRoomUseCase {
    private final RoomWsWriter roomWsWriter;
    private final SearchRoomCache searchRoomCache;
    private final PlayerCacheWriter playerCacheWriter;

    @Override
    public Room joinToPrivateRoom(JoinRoomCommandDTO joinRoomCommandDTO) {
        if(!searchRoomCache.searchRoomByCode(joinRoomCommandDTO.roomCode())){
            throw new RuntimeException("Room not found");
        }
        Room r = searchRoomCache.getRoomByCode(joinRoomCommandDTO.roomCode());
        roomWsWriter.joinRoom(joinRoomCommandDTO.roomCode(), joinRoomCommandDTO.guestId());
        return playerCacheWriter.addPlayerToRoom(joinRoomCommandDTO.guestId(), r.getRoomId());

    }
}
