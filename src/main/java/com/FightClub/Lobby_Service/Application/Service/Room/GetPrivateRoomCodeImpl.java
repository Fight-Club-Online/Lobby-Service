package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.GetPrivateRoomCodeUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPrivateRoomCodeImpl implements GetPrivateRoomCodeUseCase {

    private final SearchRoomCache searchRoomCache;


    @Override
    public String getPrivateRoomCode(long roomId) {
        if(!searchRoomCache.searchRoomById(roomId)) return null;
        return searchRoomCache.getRoomCode(roomId);
    }
}
