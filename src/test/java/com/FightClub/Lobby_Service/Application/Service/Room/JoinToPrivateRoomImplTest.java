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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JoinToPrivateRoomImplTest {

    @Mock
    private RoomWsWriter roomWsWriter;

    @Mock
    private SearchRoomCache searchRoomCache;

    @Mock
    private PlayerCacheWriter playerCacheWriter;

    @InjectMocks
    private JoinToPrivateRoomImpl joinToPrivateRoom;

    @Test
    void joinToPrivateRoomAddsPlayerWhenNotAlreadyInside() {
        JoinRoomCommandDTO commandDTO = new JoinRoomCommandDTO("ROOM-200", "guest-1");
        Room room = Room.builder().roomId(20L).roomCode("ROOM-200").players(List.of()).build();
        Room updatedRoom = Room.builder()
                .roomId(20L)
                .roomCode("ROOM-200")
                .players(List.of(Player.builder().userId("guest-1").playerType(PlayerType.PLAYER).build()))
                .build();

        when(searchRoomCache.searchRoomByCode("ROOM-200")).thenReturn(true);
        when(searchRoomCache.getRoomByCode("ROOM-200")).thenReturn(room);
        when(playerCacheWriter.addPlayerToRoom("guest-1", 20L)).thenReturn(updatedRoom);

        Room result = joinToPrivateRoom.joinToPrivateRoom(commandDTO);

        assertThat(result).isEqualTo(updatedRoom);
        verify(playerCacheWriter).addPlayerToRoom("guest-1", 20L);
        verify(roomWsWriter).joinRoom("ROOM-200", updatedRoom);
    }

    @Test
    void joinToPrivateRoomReturnsExistingRoomWhenPlayerAlreadyInside() {
        JoinRoomCommandDTO commandDTO = new JoinRoomCommandDTO("ROOM-200", "guest-1");
        Room room = Room.builder()
                .roomId(20L)
                .roomCode("ROOM-200")
                .players(List.of(Player.builder().userId("guest-1").playerType(PlayerType.PLAYER).build()))
                .build();

        when(searchRoomCache.searchRoomByCode("ROOM-200")).thenReturn(true);
        when(searchRoomCache.getRoomByCode("ROOM-200")).thenReturn(room);

        Room result = joinToPrivateRoom.joinToPrivateRoom(commandDTO);

        assertThat(result).isEqualTo(room);
        verify(playerCacheWriter, never()).addPlayerToRoom(anyString(), anyLong());
        verify(roomWsWriter).joinRoom("ROOM-200", room);
    }

    @Test
    void joinToPrivateRoomThrowsWhenRoomDoesNotExist() {
        JoinRoomCommandDTO commandDTO = new JoinRoomCommandDTO("ROOM-404", "guest-1");

        when(searchRoomCache.searchRoomByCode("ROOM-404")).thenReturn(false);

        assertThatThrownBy(() -> joinToPrivateRoom.joinToPrivateRoom(commandDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Room not found");

        verifyNoInteractions(playerCacheWriter, roomWsWriter);
    }
}