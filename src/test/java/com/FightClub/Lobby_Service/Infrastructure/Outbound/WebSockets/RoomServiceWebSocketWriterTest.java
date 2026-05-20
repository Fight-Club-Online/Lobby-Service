package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket.RoomStateMapperv2;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket.RoomStateDTOv2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceWebSocketWriterTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private RoomStateMapper roomStateMapper;

    @Mock
    private RoomStateMapperv2 roomStateMapperv2;

    @InjectMocks
    private RoomServiceWebSocketWriter roomServiceWebSocketWriter;

    @Test
    void testJoinRoomSuccessfully() {
        // Arrange
        String roomId = "1";
        Room room = Room.builder()
                .roomId(1L)
                .roomCode("ROOM-001")
                .build();
        RoomStateDTO expectedPayload = new RoomStateDTO();
        when(roomStateMapper.toDto(room)).thenReturn(expectedPayload);

        // Act
        roomServiceWebSocketWriter.joinRoom(roomId, room);

        // Assert
        verify(messagingTemplate, times(1)).convertAndSend(
                eq("/room/" + roomId),
            eq(expectedPayload)
        );
    }

    @Test
    void testLeaveRoomSuccessfully() {
        // Arrange
        String roomId = "1";
        String guestId = "user123";

        // Act
        roomServiceWebSocketWriter.leaveRoom(roomId, guestId);

        // Assert
        verify(messagingTemplate, times(1)).convertAndSend(
                eq("/room/" + roomId),
                eq((Object) (guestId + " ha salido"))
        );
    }

    @Test
    void testStartGameSuccessfully() {
        // Arrange
        Room room = Room.builder()
                .roomId(1L)
                .roomCode("ROOM-001")
                .build();
        RoomStateDTOv2 expectedPayload = new RoomStateDTOv2();
        when(roomStateMapperv2.toDto(room)).thenReturn(expectedPayload);

        // Act
        roomServiceWebSocketWriter.StartGame(room);

        // Assert
        verify(messagingTemplate, times(1)).convertAndSend(
                eq("/room/" + room.getRoomCode()),
            eq(expectedPayload)
        );
    }

    @Test
    void testJoinRoomWithDifferentRoomIds() {
        // Arrange
        String[] roomIds = {"1", "2", "3"};
        Room room = Room.builder().build();
        RoomStateDTO expectedPayload = new RoomStateDTO();

        when(roomStateMapper.toDto(any(Room.class))).thenReturn(expectedPayload);

        for (String roomId : roomIds) {
            // Act
            roomServiceWebSocketWriter.joinRoom(roomId, room);

            // Assert
            verify(messagingTemplate).convertAndSend(
                    eq("/room/" + roomId),
                    eq(expectedPayload)
            );
        }
    }

    @Test
    void testLeaveRoomWithMultipleUsers() {
        // Arrange
        String roomId = "1";
        String[] userIds = {"user1", "user2", "user3"};

        for (String userId : userIds) {
            // Act
            roomServiceWebSocketWriter.leaveRoom(roomId, userId);

            // Assert
            verify(messagingTemplate).convertAndSend(
                    eq("/room/" + roomId),
                    eq((Object) (userId + " ha salido"))
            );
        }
    }

    @Test
    void testStartGameWithDifferentRooms() {
        // Arrange
        for (int i = 1; i <= 3; i++) {
            Room room = Room.builder()
                    .roomId((long) i)
                    .roomCode("ROOM-00" + i)
                    .build();
            RoomStateDTOv2 expectedPayload = new RoomStateDTOv2();

            when(roomStateMapperv2.toDto(room)).thenReturn(expectedPayload);

            // Act
            roomServiceWebSocketWriter.StartGame(room);

            // Assert
            verify(messagingTemplate).convertAndSend(
                eq("/room/" + room.getRoomCode()),
                eq(expectedPayload)
            );
        }
    }

    @Test
    void testJoinRoomSendsMessage() {
        // Arrange
        String roomId = "5";
        Room room = Room.builder()
                .roomId(5L)
                .roomCode("ROOM-005")
                .currentPlayers(1)
                .maxPlayers(2)
                .build();
        RoomStateDTO expectedPayload = new RoomStateDTO();
        when(roomStateMapper.toDto(room)).thenReturn(expectedPayload);

        // Act
        roomServiceWebSocketWriter.joinRoom(roomId, room);

        // Assert
        verify(messagingTemplate, times(1)).convertAndSend(eq("/room/" + roomId), eq(expectedPayload));
    }

    @Test
    void testLeaveRoomSendsFormattedMessage() {
        // Arrange
        String roomId = "10";
        String guestId = "user456";
        String expectedMessage = "user456 ha salido";

        // Act
        roomServiceWebSocketWriter.leaveRoom(roomId, guestId);

        // Assert
        verify(messagingTemplate, times(1)).convertAndSend(
                eq("/room/" + roomId),
            eq((Object) expectedMessage)
        );
    }
}
