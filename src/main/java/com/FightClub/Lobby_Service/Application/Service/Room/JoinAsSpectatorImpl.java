package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsSpectatorUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinAsSpectatorImpl implements JoinAsSpectatorUseCase {

    private final SearchRoomCache searchRoomCache;
    private final PlayerCacheWriter playerCacheWriter;
    private final RoomWsWriter roomWsWriter;

    @Override
    public Room joinAsSpectator(JoinRoomCommandDTO joinRoomCommandDTO) {
        Room r = searchRoomCache.getRoomByCode(joinRoomCommandDTO.roomCode());
        roomWsWriter.joinRoom(joinRoomCommandDTO.roomCode(), joinRoomCommandDTO.guestId());
        return playerCacheWriter.addPlayerToRoomByRole(joinRoomCommandDTO.guestId(),r.getRoomId(), PlayerType.SPECTATOR);
    }

}
