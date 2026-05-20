package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
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
class CreatePrivateRoomImplTest {

    @Mock
    private RoomCacheWriter roomCacheWriter;

    @InjectMocks
    private CreatePrivateRoomImpl createPrivateRoom;

    @Test
    void testCreatePrivateRoomSuccessfully() {
        // Arrange
        String hostId = "user123";

        // Act
        Room result = createPrivateRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getHostId()).isEqualTo(hostId);
        assertThat(result.isPublic()).isFalse();
        assertThat(result.getMaxPlayers()).isEqualTo(2);
        assertThat(result.getMaxSpectators()).isEqualTo(5);
        assertThat(result.getCurrentPlayers()).isEqualTo(1);
        assertThat(result.getCurrentSpectators()).isEqualTo(0);
        assertThat(result.getRoomState()).isEqualTo(RoomState.WAITING);
        verify(roomCacheWriter, times(1)).saveRoom(any(Room.class));
    }

    @Test
    void testCreatePrivateRoomVerifiesRoomCode() {
        // Arrange
        String hostId = "user456";

        // Act
        Room result = createPrivateRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result.getRoomCode()).startsWith("ROOM-");
        assertThat(result.getRoomCode()).contains(hostId);
    }

    @Test
    void testCreatePrivateRoomCreatesPlayer() {
        // Arrange
        String hostId = "user789";

        // Act
        Room result = createPrivateRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result.getPlayers()).isNotEmpty();
        assertThat(result.getPlayers().get(0).getUserId()).isEqualTo(hostId);
        assertThat(result.getPlayers().get(0).getPlayerType()).isEqualTo(PlayerType.PLAYER);
    }

    @Test
    void testCreatePrivateRoomHasUniqueRoomId() {
        // Arrange
        String hostId = "user123";

        // Act
        Room result1 = createPrivateRoom.createPrivateRoom(hostId);
        Room result2 = createPrivateRoom.createPrivateRoom(hostId);

        // Assert
        assertThat(result1.getRoomId()).isNotEqualTo(result2.getRoomId());
    }

    @Test
    void testCreatePrivateRoomSavesInCache() {
        // Arrange
        String hostId = "user123";

        // Act
        createPrivateRoom.createPrivateRoom(hostId);

        // Assert
        ArgumentCaptor<Room> captor = ArgumentCaptor.forClass(Room.class);
        verify(roomCacheWriter).saveRoom(captor.capture());
        Room capturedRoom = captor.getValue();
        assertThat(capturedRoom.isPublic()).isFalse();
        assertThat(capturedRoom.getHostId()).isEqualTo(hostId);
    }

    @Test
    void testCreatePrivateRoomWithDifferentHosts() {
        // Arrange & Act & Assert
        for (int i = 0; i < 3; i++) {
            String hostId = "user" + i;
            Room result = createPrivateRoom.createPrivateRoom(hostId);
            assertThat(result.getHostId()).isEqualTo(hostId);
            assertThat(result.isPublic()).isFalse();
        }

        verify(roomCacheWriter, times(3)).saveRoom(any(Room.class));
    }
}
