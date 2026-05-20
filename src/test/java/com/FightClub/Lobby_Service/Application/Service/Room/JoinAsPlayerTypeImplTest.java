package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomPTCommandDTO;
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
class JoinAsPlayerTypeImplTest {

    @Mock
    private SearchRoomCache searchRoomCache;

    @Mock
    private PlayerCacheWriter playerCacheWriter;

    @Mock
    private RoomWsWriter roomWsWriter;

    @InjectMocks
    private JoinAsPlayerTypeImpl joinAsPlayerType;

    @Test
    void joinAsPlayerTypeReturnsUpdatedRoomWhenPlayerIsNew() {
        JoinRoomPTCommandDTO commandDTO = new JoinRoomPTCommandDTO("ROOM-123", "guest-1", PlayerType.PLAYER);
        Room room = Room.builder()
                .roomId(10L)
                .roomCode("ROOM-123")
                .players(List.of())
                .build();
        Room updatedRoom = Room.builder()
                .roomId(10L)
                .roomCode("ROOM-123")
                .players(List.of(Player.builder().userId("guest-1").playerType(PlayerType.PLAYER).build()))
                .build();

        when(searchRoomCache.searchRoomByCode("ROOM-123")).thenReturn(true);
        when(searchRoomCache.getRoomByCode("ROOM-123")).thenReturn(room);
        when(playerCacheWriter.addPlayerToRoomByRole("guest-1", 10L, PlayerType.PLAYER)).thenReturn(updatedRoom);

        Room result = joinAsPlayerType.JoinAsPlayerType(commandDTO);

        assertThat(result).isEqualTo(updatedRoom);
        verify(searchRoomCache).searchRoomByCode("ROOM-123");
        verify(searchRoomCache).getRoomByCode("ROOM-123");
        verify(playerCacheWriter).addPlayerToRoomByRole("guest-1", 10L, PlayerType.PLAYER);
        verify(roomWsWriter).joinRoom("ROOM-123", updatedRoom);
    }

    @Test
    void joinAsPlayerTypeReturnsExistingRoomWhenPlayerAlreadyExists() {
        JoinRoomPTCommandDTO commandDTO = new JoinRoomPTCommandDTO("ROOM-123", "guest-1", PlayerType.PLAYER);
        Room room = Room.builder()
                .roomId(10L)
                .roomCode("ROOM-123")
                .players(List.of(Player.builder().userId("guest-1").playerType(PlayerType.PLAYER).build()))
                .build();

        when(searchRoomCache.searchRoomByCode("ROOM-123")).thenReturn(true);
        when(searchRoomCache.getRoomByCode("ROOM-123")).thenReturn(room);

        Room result = joinAsPlayerType.JoinAsPlayerType(commandDTO);

        assertThat(result).isEqualTo(room);
        verify(playerCacheWriter, never()).addPlayerToRoomByRole(anyString(), anyLong(), any());
        verify(roomWsWriter).joinRoom("ROOM-123", room);
    }

    @Test
    void joinAsPlayerTypeThrowsWhenRoomDoesNotExist() {
        JoinRoomPTCommandDTO commandDTO = new JoinRoomPTCommandDTO("ROOM-404", "guest-1", PlayerType.PLAYER);

        when(searchRoomCache.searchRoomByCode("ROOM-404")).thenReturn(false);

        assertThatThrownBy(() -> joinAsPlayerType.JoinAsPlayerType(commandDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Room not found");

        verify(searchRoomCache).searchRoomByCode("ROOM-404");
        verifyNoInteractions(playerCacheWriter, roomWsWriter);
    }
}