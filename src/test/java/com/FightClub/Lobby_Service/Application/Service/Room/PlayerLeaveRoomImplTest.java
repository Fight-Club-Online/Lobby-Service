package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerLeaveRoomImplTest {

    @Mock
    private PlayerCacheWriter playerCacheWriter;

    @Mock
    private RoomWsWriter roomWsWriter;

    @InjectMocks
    private PlayerLeaveRoomImpl playerLeaveRoom;

    @Test
    void testLeaveRoomSuccessfully() {
        // Arrange
        String userId = "user123";
        Room expectedRoom = Room.builder()
                .roomId(1L)
                .roomCode("ROOM-001")
                .currentPlayers(1)
                .build();

        when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

        // Act
        playerLeaveRoom.leaveRoom(userId);

        // Assert
        verify(playerCacheWriter, times(1)).leaveRoom(userId);
        verify(roomWsWriter, times(1)).leaveRoom("1", userId);
    }

    @Test
    void testLeaveRoomCallsPlayerCacheWriter() {
        // Arrange
        String userId = "user456";
        Room expectedRoom = Room.builder()
                .roomId(2L)
                .roomCode("ROOM-002")
                .build();

        when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

        // Act
        playerLeaveRoom.leaveRoom(userId);

        // Assert
        verify(playerCacheWriter, times(1)).leaveRoom(userId);
    }

    @Test
    void testLeaveRoomCallsWebSocketWriter() {
        // Arrange
        String userId = "user789";
        Room expectedRoom = Room.builder()
                .roomId(3L)
                .roomCode("ROOM-003")
                .build();

        when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

        // Act
        playerLeaveRoom.leaveRoom(userId);

        // Assert
        verify(roomWsWriter, times(1)).leaveRoom("3", userId);
    }

    @Test
    void testLeaveRoomWithDifferentUsers() {
        // Arrange
        String[] userIds = {"user1", "user2", "user3"};
        long roomId = 1L;

        for (String userId : userIds) {
            Room expectedRoom = Room.builder()
                    .roomId(roomId)
                    .roomCode("ROOM-001")
                    .build();

            when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

            // Act
            playerLeaveRoom.leaveRoom(userId);

            // Assert
            verify(playerCacheWriter).leaveRoom(userId);
            verify(roomWsWriter).leaveRoom(String.valueOf(roomId), userId);
        }
    }

    @Test
    void testLeaveRoomConvertsRoomIdToString() {
        // Arrange
        String userId = "user123";
        long roomId = 12345L;
        Room expectedRoom = Room.builder()
                .roomId(roomId)
                .roomCode("ROOM-TEST")
                .build();

        when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

        // Act
        playerLeaveRoom.leaveRoom(userId);

        // Assert
        verify(roomWsWriter).leaveRoom(String.valueOf(roomId), userId);
    }

    @Test
    void testLeaveRoomNotifiesAllListeners() {
        // Arrange
        String userId = "user999";
        Room expectedRoom = Room.builder()
                .roomId(99L)
                .roomCode("ROOM-999")
                .build();

        when(playerCacheWriter.leaveRoom(userId)).thenReturn(expectedRoom);

        // Act
        playerLeaveRoom.leaveRoom(userId);

        // Assert
        verify(playerCacheWriter, times(1)).leaveRoom(userId);
        verify(roomWsWriter, times(1)).leaveRoom("99", userId);
        verifyNoMoreInteractions(playerCacheWriter, roomWsWriter);
    }
}
