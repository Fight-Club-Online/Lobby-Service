package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.DTO.JoinRoomPTCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsPlayerTypeUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsSpectatorUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket.RoomSocketRequestDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomSocketControllerTest {

    @Mock
    private JoinToPrivateRoomUseCase jointoPrivateRoomUseCase;

    @Mock
    private JoinAsSpectatorUseCase joinAsSpectatorUseCase;

    @Mock
    private PlayerLeaveRoomUseCase playerLeaveRoomUseCase;

    @Mock
    private RoomStateMapper roomStateMapper;

    @Mock
    private JoinAsPlayerTypeUseCase joinAsPlayerTypeUseCase;

    @Mock
    private SimpMessageHeaderAccessor headerAccessor;

    @InjectMocks
    private RoomSocketController controller;

    @Test
    void testJoinRoomSuccessfully() {
        // Arrange
        RoomSocketRequestDTO roomInfo = new RoomSocketRequestDTO();
        roomInfo.setRoomCode("ROOM-001");
        roomInfo.setUserId("user123");
        roomInfo.setPlayerType("PLAYER");

        Map<String, Object> sessionAttributes = new HashMap<>();
        when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

        Room expectedRoom = Room.builder()
                .roomCode("ROOM-001")
                .roomState(RoomState.WAITING)
                .build();

        when(joinAsPlayerTypeUseCase.JoinAsPlayerType(any(JoinRoomPTCommandDTO.class)))
                .thenReturn(expectedRoom);
        when(roomStateMapper.toDto(expectedRoom)).thenReturn(new RoomStateDTO());

        // Act
        controller.joinRoom(roomInfo, headerAccessor);

        // Assert
        assertThat(sessionAttributes).containsEntry("userId", "user123");
        assertThat(sessionAttributes).containsEntry("roomCode", "ROOM-001");
        verify(joinAsPlayerTypeUseCase, times(1)).JoinAsPlayerType(any(JoinRoomPTCommandDTO.class));
    }

    @Test
    void testJoinRoomAsSpectatorSuccessfully() {
        // Arrange
        RoomSocketRequestDTO roomInfo = new RoomSocketRequestDTO();
        roomInfo.setRoomCode("ROOM-001");
        roomInfo.setUserId("user123");

        Room expectedRoom = Room.builder()
                .roomCode("ROOM-001")
                .build();

        when(joinAsSpectatorUseCase.joinAsSpectator(any(JoinRoomCommandDTO.class)))
                .thenReturn(expectedRoom);
        when(roomStateMapper.toDto(expectedRoom)).thenReturn(new RoomStateDTO());

        // Act
        RoomStateDTO result = controller.joinRoomAsSpectator(roomInfo);

        // Assert
        assertThat(result).isNotNull();
        verify(joinAsSpectatorUseCase, times(1)).joinAsSpectator(any(JoinRoomCommandDTO.class));
    }

    @Test
    void testLeaveRoomSuccessfully() {
        // Arrange
        String userId = "user123";

        // Act
        controller.leaveRoom(userId);

        // Assert
        verify(playerLeaveRoomUseCase, times(1)).leaveRoom(userId);
    }

    @Test
    void testJoinRoomSetsSessionAttributes() {
        // Arrange
        RoomSocketRequestDTO roomInfo = new RoomSocketRequestDTO();
        roomInfo.setRoomCode("ROOM-TEST");
        roomInfo.setUserId("user999");
        roomInfo.setPlayerType("SPECTATOR");

        Map<String, Object> sessionAttributes = new HashMap<>();
        when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

        Room expectedRoom = Room.builder().build();
        when(joinAsPlayerTypeUseCase.JoinAsPlayerType(any(JoinRoomPTCommandDTO.class)))
                .thenReturn(expectedRoom);

        // Act
        controller.joinRoom(roomInfo, headerAccessor);

        // Assert
        assertThat(sessionAttributes).containsEntry("userId", "user999");
        assertThat(sessionAttributes).containsEntry("roomCode", "ROOM-TEST");
    }

    @Test
    void testJoinRoomWithNullSessionAttributes() {
        // Arrange
        RoomSocketRequestDTO roomInfo = new RoomSocketRequestDTO();
        roomInfo.setRoomCode("ROOM-001");
        roomInfo.setUserId("user123");
        roomInfo.setPlayerType("PLAYER");

        when(headerAccessor.getSessionAttributes()).thenReturn(null);

        Room expectedRoom = Room.builder().build();
        when(joinAsPlayerTypeUseCase.JoinAsPlayerType(any(JoinRoomPTCommandDTO.class)))
                .thenReturn(expectedRoom);

        // Act
        controller.joinRoom(roomInfo, headerAccessor);

        // Assert
        verify(joinAsPlayerTypeUseCase, times(1)).JoinAsPlayerType(any(JoinRoomPTCommandDTO.class));
    }

    @Test
    void testMultipleLeaveRoomCalls() {
        // Arrange
        String[] userIds = {"user1", "user2", "user3"};

        for (String userId : userIds) {
            // Act
            controller.leaveRoom(userId);

            // Assert
            verify(playerLeaveRoomUseCase).leaveRoom(userId);
        }
    }
}
