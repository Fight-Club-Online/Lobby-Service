package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Application.Ports.Output.SendMessageProducer;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StartRoomImplTest {

    @Mock
    private RoomCacheWriter roomCacheWriter;

    @Mock
    private SendMessageProducer sendMessageProducer;

    @Mock
    private RoomWsWriter roomWsWriter;

    @InjectMocks
    private StartRoomImpl startRoom;

    @Test
    void testStartRoomSuccessfully() {
        // Arrange
        String roomCode = "ROOM-001";
        Room expectedRoom = Room.builder()
                .roomId(1L)
                .roomCode(roomCode)
                .roomState(RoomState.PLAYING)
                .currentPlayers(2)
                .maxPlayers(2)
                .build();

        when(roomCacheWriter.startRoom(roomCode)).thenReturn(expectedRoom);

        // Act
        Room result = startRoom.startRoom(roomCode);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getRoomCode()).isEqualTo(roomCode);
        assertThat(result.getRoomState()).isEqualTo(RoomState.PLAYING);
        verify(roomCacheWriter, times(1)).startRoom(roomCode);
        verify(sendMessageProducer, times(1)).sendRoom(expectedRoom);
        verify(roomWsWriter, times(1)).StartGame(expectedRoom);
    }

    @Test
    void testStartRoomCallsSendMessageProducer() {
        // Arrange
        String roomCode = "ROOM-002";
        Room expectedRoom = Room.builder()
                .roomId(2L)
                .roomCode(roomCode)
                .roomState(RoomState.PLAYING)
                .build();

        when(roomCacheWriter.startRoom(roomCode)).thenReturn(expectedRoom);

        // Act
        startRoom.startRoom(roomCode);

        // Assert
        verify(sendMessageProducer, times(1)).sendRoom(expectedRoom);
    }

    @Test
    void testStartRoomCallsWebSocketWriter() {
        // Arrange
        String roomCode = "ROOM-003";
        Room expectedRoom = Room.builder()
                .roomId(3L)
                .roomCode(roomCode)
                .build();

        when(roomCacheWriter.startRoom(roomCode)).thenReturn(expectedRoom);

        // Act
        startRoom.startRoom(roomCode);

        // Assert
        verify(roomWsWriter, times(1)).StartGame(expectedRoom);
    }

    @Test
    void testStartRoomWithDifferentRoomCodes() {
        // Arrange
        String[] roomCodes = {"ROOM-001", "ROOM-002", "ROOM-003"};

        for (String roomCode : roomCodes) {
            Room expectedRoom = Room.builder()
                    .roomCode(roomCode)
                    .roomState(RoomState.PLAYING)
                    .build();

            when(roomCacheWriter.startRoom(roomCode)).thenReturn(expectedRoom);

            // Act
            Room result = startRoom.startRoom(roomCode);

            // Assert
            assertThat(result.getRoomCode()).isEqualTo(roomCode);
        }
    }

    @Test
    void testStartRoomReturnsRoomFromCache() {
        // Arrange
        String roomCode = "ROOM-004";
        Room expectedRoom = Room.builder()
                .roomId(4L)
                .roomCode(roomCode)
                .currentPlayers(2)
                .maxPlayers(2)
                .build();

        when(roomCacheWriter.startRoom(roomCode)).thenReturn(expectedRoom);

        // Act
        Room result = startRoom.startRoom(roomCode);

        // Assert
        assertThat(result.getMaxPlayers()).isEqualTo(2);
        assertThat(result.getCurrentPlayers()).isEqualTo(2);
    }
}
