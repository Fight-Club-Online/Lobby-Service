package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetRoomAvailabilityImplTest {

    @Mock
    private SearchRoomCache searchRoomCache;

    @InjectMocks
    private GetRoomAvailabilityImpl getRoomAvailability;

    @Test
    void getRoomAvailabilityReturnsRoomFromCache() {
        Room room = Room.builder().roomId(400L).roomCode("ROOM-400").build();
        when(searchRoomCache.getRoomByCode("ROOM-400")).thenReturn(room);

        Room result = getRoomAvailability.getRoomAvailability("ROOM-400");

        assertThat(result).isEqualTo(room);
        verify(searchRoomCache).getRoomByCode("ROOM-400");
    }

    @Test
    void getRoomAvailabilityReturnsNullWhenRoomDoesNotExist() {
        when(searchRoomCache.getRoomByCode("ROOM-404")).thenReturn(null);

        Room result = getRoomAvailability.getRoomAvailability("ROOM-404");

        assertThat(result).isNull();
        verify(searchRoomCache).getRoomByCode("ROOM-404");
    }
}