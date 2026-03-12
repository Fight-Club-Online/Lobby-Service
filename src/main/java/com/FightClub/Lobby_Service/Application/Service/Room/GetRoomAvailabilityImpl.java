package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.GetRoomAvailabilityUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetRoomAvailabilityImpl implements GetRoomAvailabilityUseCase {

    private final SearchRoomCache searchRoomCache;


    @Override
    public int getRoomAvailability(String roomCode){
        return searchRoomCache.getRoomAvailability(roomCode);
    }

}
