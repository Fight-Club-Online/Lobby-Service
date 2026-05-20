package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePublicRoomImplTest {

    @Mock
    private RoomCacheWriter roomCacheWriter;

    @InjectMocks
    private CreatePublicRoomImpl createPublicRoom;

    @Test
    void testCreatePrivateRoomSuccessfully() {
        // Arrange
        String hostId = "user123";

        // Act
        Room result = createPublicRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getHostId()).isEqualTo(hostId);
        assertThat(result.isPublic()).isTrue();
        assertThat(result.getMaxPlayers()).isEqualTo(2);
        assertThat(result.getCurrentPlayers()).isEqualTo(1);
        assertThat(result.getRoomState()).isNotNull();
        verify(roomCacheWriter, times(1)).saveRoom(any(Room.class));
    }

    @Test
    void testCreatePrivateRoomVerifiesRoomCode() {
        // Arrange
        String hostId = "user456";

        // Act
        Room result = createPublicRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result.getRoomCode()).startsWith("ROOM-");
        assertThat(result.getRoomCode()).contains(hostId);
    }

    @Test
    void testCreatePrivateRoomVerifiesPlayer() {
        // Arrange
        String hostId = "user789";

        // Act
        Room result = createPublicRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result.getPlayers()).isNotEmpty();
        assertThat(result.getPlayers().get(0).getUserId()).isEqualTo(hostId);
    }

    @Test
    void testCreatePrivateRoomSavesRoomInCache() {
        // Arrange
        String hostId = "user123";

        // Act
        createPublicRoom.createPrivateRoom(hostId);

        // Assert
        ArgumentCaptor<Room> captor = ArgumentCaptor.forClass(Room.class);
        verify(roomCacheWriter).saveRoom(captor.capture());
        Room capturedRoom = captor.getValue();
        assertThat(capturedRoom.getHostId()).isEqualTo(hostId);
    }

    @Test
    void testCreatePrivateRoomWithDifferentHosts() {
        // Arrange
        String[] hostIds = {"user1", "user2", "user3"};

        for (String hostId : hostIds) {
            // Act
            Room result = createPublicRoom.createPrivateRoom(hostId);

            // Assert
            assertThat(result.getHostId()).isEqualTo(hostId);
        }

        verify(roomCacheWriter, times(3)).saveRoom(any(Room.class));
    }
}
