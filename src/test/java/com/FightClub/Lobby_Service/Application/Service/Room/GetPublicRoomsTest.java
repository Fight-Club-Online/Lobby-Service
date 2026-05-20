package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPublicRoomsTest {

    @Mock
    private SearchRoomCache searchRoomCache;

    @InjectMocks
    private GetPublicRooms getPublicRooms;

    @Test
    void testGetPublicRoomsWhenAvailable() {
        // Arrange
        Room room1 = Room.builder()
                .roomId(1L)
                .roomCode("ROOM-001")
                .isPublic(true)
                .currentPlayers(1)
                .maxPlayers(2)
                .build();

        Room room2 = Room.builder()
                .roomId(2L)
                .roomCode("ROOM-002")
                .isPublic(true)
                .currentPlayers(2)
                .maxPlayers(2)
                .build();

        List<Room> expectedRooms = Arrays.asList(room1, room2);
        when(searchRoomCache.getPublicRooms()).thenReturn(expectedRooms);

        // Act
        List<Room> result = getPublicRooms.getPublicRooms();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).contains(room1, room2);
        verify(searchRoomCache, times(1)).getPublicRooms();
    }

    @Test
    void testGetPublicRoomsWhenEmpty() {
        // Arrange
        when(searchRoomCache.getPublicRooms()).thenReturn(Collections.emptyList());

        // Act
        List<Room> result = getPublicRooms.getPublicRooms();

        // Assert
        assertThat(result).isEmpty();
        verify(searchRoomCache, times(1)).getPublicRooms();
    }

    @Test
    void testGetPublicRoomsWithMultipleRooms() {
        // Arrange
        List<Room> expectedRooms = Arrays.asList(
                Room.builder().roomId(1L).roomCode("ROOM-001").isPublic(true).build(),
                Room.builder().roomId(2L).roomCode("ROOM-002").isPublic(true).build(),
                Room.builder().roomId(3L).roomCode("ROOM-003").isPublic(true).build()
        );

        when(searchRoomCache.getPublicRooms()).thenReturn(expectedRooms);

        // Act
        List<Room> result = getPublicRooms.getPublicRooms();

        // Assert
        assertThat(result).hasSize(3);
    }
}
