package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.GetPublicRoomsUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPublicRooms implements GetPublicRoomsUseCase {
    private final SearchRoomCache searchRoomCache;
    @Override
    public List<Room> getPublicRooms() {
        return searchRoomCache.getPublicRooms();
    }
}
