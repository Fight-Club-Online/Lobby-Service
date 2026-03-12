package com.FightClub.Lobby_Service.Application.Service.Room;


import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerLeaveRoomImpl implements PlayerLeaveRoomUseCase {

    private final PlayerCacheWriter playerCacheWriter;

    @Override
    public void leaveRoom(String userId){
        playerCacheWriter.leaveRoom(userId);
    }
}
