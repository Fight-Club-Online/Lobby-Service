package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomPTCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsPlayerTypeUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinAsPlayerTypeImpl implements JoinAsPlayerTypeUseCase {
    private final SearchRoomCache searchRoomCache;
    private final PlayerCacheWriter playerCacheWriter;
    private final RoomWsWriter roomWsWriter;

    @Override
    public Room JoinAsPlayerType(JoinRoomPTCommandDTO joinRoomPTCommandDTO) {
        if(!searchRoomCache.searchRoomByCode(joinRoomPTCommandDTO.roomCode())){
            throw new RuntimeException("Room not found");
        }

        Room r = searchRoomCache.getRoomByCode(joinRoomPTCommandDTO.roomCode());
        boolean alreadyIn = r.getPlayers().stream()
                .anyMatch(p -> p.getUserId().equals(joinRoomPTCommandDTO.guestId()));

        if (alreadyIn) {
            roomWsWriter.joinRoom(joinRoomPTCommandDTO.roomCode(), r);
            return r;
        }
        Room updatedRoom = playerCacheWriter.addPlayerToRoomByRole(joinRoomPTCommandDTO.guestId(),r.getRoomId(),joinRoomPTCommandDTO.playerType() );

        roomWsWriter.joinRoom(joinRoomPTCommandDTO.roomCode(), updatedRoom);
        return updatedRoom;
    }
}
