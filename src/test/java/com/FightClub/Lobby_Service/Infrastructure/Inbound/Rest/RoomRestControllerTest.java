package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.*;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseMapper;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomRestControllerTest {

    @Mock
    private GetPublicRoomsUseCase getPublicRoomsUseCase;

    @Mock
    private CreatePublicRoomUseCase createPublicRoomUseCase;

    @Mock
    private CreatePrivateRoomUseCase createPrivateRoomUseCase;

    @Mock
    private GetPrivateRoomCodeUseCase getPrivateRoomCodeUseCase;

    @Mock
    private GetRoomAvailabilityUseCase getRoomAvailabilityUseCase;

    @Mock
    private StartRoomUseCase startRoomUseCase;

    @Mock
    private RoomResponseMapper roomResponseMapper;

    @Mock
    private RoomStateMapper roomStateMapper;

    @InjectMocks
    private RoomRestController controller;

    @Test
    void testCreatePrivateRoomSuccess() {
        // Arrange
        String hostId = "user123";
        Room expectedRoom = Room.builder()
                .roomId(1L)
                .roomCode("ROOM-001")
                .isPublic(false)
                .build();
        RoomResponseDTO expectedDTO = new RoomResponseDTO();

        when(createPrivateRoomUseCase.createPrivateRoom(hostId)).thenReturn(expectedRoom);
        when(roomResponseMapper.toDTO(expectedRoom)).thenReturn(expectedDTO);

        // Act
        RoomResponseDTO result = controller.createPrivateRoom(hostId);

        // Assert
        assertThat(result).isNotNull();
        verify(createPrivateRoomUseCase, times(1)).createPrivateRoom(hostId);
        verify(roomResponseMapper, times(1)).toDTO(expectedRoom);
    }

    @Test
    void testCreatePublicRoomSuccess() {
        // Arrange
        String hostId = "user123";
        Room expectedRoom = Room.builder()
                .roomId(2L)
                .roomCode("ROOM-002")
                .isPublic(true)
                .build();
        RoomResponseDTO expectedDTO = new RoomResponseDTO();

        when(createPublicRoomUseCase.createPrivateRoom(hostId)).thenReturn(expectedRoom);
        when(roomResponseMapper.toDTO(expectedRoom)).thenReturn(expectedDTO);

        // Act
        RoomResponseDTO result = controller.createPublicRoom(hostId);

        // Assert
        assertThat(result).isNotNull();
        verify(createPublicRoomUseCase, times(1)).createPrivateRoom(hostId);
    }

    @Test
    void testGetPrivateRoomCodeSuccess() {
        // Arrange
        long roomId = 1L;
        String expectedCode = "ROOM-001";

        when(getPrivateRoomCodeUseCase.getPrivateRoomCode(roomId)).thenReturn(expectedCode);

        // Act
        String result = controller.getPrivateRoomCode(roomId);

        // Assert
        assertThat(result).isEqualTo(expectedCode);
        verify(getPrivateRoomCodeUseCase, times(1)).getPrivateRoomCode(roomId);
    }

    @Test
    void testGetRoomAvailabilitySuccess() {
        // Arrange
        String roomCode = "ROOM-001";
        Room expectedRoom = Room.builder()
            .roomId(3L)
            .roomCode(roomCode)
            .isPublic(true)
            .build();
        RoomStateDTO expectedDTO = new RoomStateDTO();

        when(getRoomAvailabilityUseCase.getRoomAvailability(roomCode)).thenReturn(expectedRoom);
        when(roomStateMapper.toDto(expectedRoom)).thenReturn(expectedDTO);

        // Act
        RoomStateDTO result = controller.getRoomAvailability(roomCode);

        // Assert
        assertThat(result).isNotNull();
        verify(getRoomAvailabilityUseCase, times(1)).getRoomAvailability(roomCode);
        verify(roomStateMapper, times(1)).toDto(expectedRoom);
    }

    @Test
    void testStartFightSuccess() {
        // Arrange
        String roomCode = "ROOM-001";
        Room expectedRoom = Room.builder()
                .roomCode(roomCode)
                .roomState(RoomState.PLAYING)
                .build();
        RoomResponseDTO expectedDTO = new RoomResponseDTO();

        when(startRoomUseCase.startRoom(roomCode)).thenReturn(expectedRoom);
        when(roomResponseMapper.toDTO(expectedRoom)).thenReturn(expectedDTO);

        // Act
        RoomResponseDTO result = controller.startFight(roomCode);

        // Assert
        assertThat(result).isNotNull();
        verify(startRoomUseCase, times(1)).startRoom(roomCode);
    }

    @Test
    void testGetPublicRoomsSuccess() {
        // Arrange
        List<Room> rooms = Arrays.asList(
                Room.builder().roomId(1L).roomCode("ROOM-001").isPublic(true).build(),
                Room.builder().roomId(2L).roomCode("ROOM-002").isPublic(true).build()
        );

        when(getPublicRoomsUseCase.getPublicRooms()).thenReturn(rooms);
        when(roomResponseMapper.toDTO(any(Room.class)))
                .thenReturn(new RoomResponseDTO())
                .thenReturn(new RoomResponseDTO());

        // Act
        List<RoomResponseDTO> result = controller.getPublicRooms();

        // Assert
        assertThat(result).hasSize(2);
        verify(getPublicRoomsUseCase, times(1)).getPublicRooms();
    }
}
