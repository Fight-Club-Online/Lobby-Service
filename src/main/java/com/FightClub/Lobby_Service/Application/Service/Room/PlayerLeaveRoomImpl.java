package com.FightClub.Lobby_Service.Application.Service.Room;


import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerLeaveRoomImpl implements PlayerLeaveRoomUseCase {

    private final PlayerCacheWriter playerCacheWriter;
    private final RoomWsWriter roomWsWriter;

    @Override
    public void leaveRoom(String userId){
        Room r = playerCacheWriter.leaveRoom(userId);
        String rId = String.valueOf(r.getRoomId());
        roomWsWriter.leaveRoom(rId,userId);
    }
}
