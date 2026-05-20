package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Player;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JoinAsSpectatorImplTest {

    @Mock
    private SearchRoomCache searchRoomCache;

    @Mock
    private PlayerCacheWriter playerCacheWriter;

    @Mock
    private RoomWsWriter roomWsWriter;

    @InjectMocks
    private JoinAsSpectatorImpl joinAsSpectator;

    @Test
    void joinAsSpectatorAddsSpectatorRoleAndBroadcastsRoom() {
        JoinRoomCommandDTO commandDTO = new JoinRoomCommandDTO("ROOM-300", "guest-1");
        Room room = Room.builder().roomId(30L).roomCode("ROOM-300").players(List.of()).build();
        Room updatedRoom = Room.builder()
                .roomId(30L)
                .roomCode("ROOM-300")
                .players(List.of(Player.builder().userId("guest-1").playerType(PlayerType.SPECTATOR).build()))
                .build();

        when(searchRoomCache.getRoomByCode("ROOM-300")).thenReturn(room);
        when(playerCacheWriter.addPlayerToRoomByRole("guest-1", 30L, PlayerType.SPECTATOR)).thenReturn(updatedRoom);

        Room result = joinAsSpectator.joinAsSpectator(commandDTO);

        assertThat(result).isEqualTo(updatedRoom);
        verify(searchRoomCache).getRoomByCode("ROOM-300");
        verify(playerCacheWriter).addPlayerToRoomByRole("guest-1", 30L, PlayerType.SPECTATOR);
        verify(roomWsWriter).joinRoom("ROOM-300", updatedRoom);
    }
}