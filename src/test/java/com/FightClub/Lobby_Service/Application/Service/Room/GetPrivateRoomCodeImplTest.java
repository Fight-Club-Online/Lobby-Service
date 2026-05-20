package com.FightClub.Lobby_Service.Application.Service.Room;

import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPrivateRoomCodeImplTest {

    @Mock
    private SearchRoomCache searchRoomCache;

    @InjectMocks
    private GetPrivateRoomCodeImpl getPrivateRoomCode;

    @Test
    void getPrivateRoomCodeReturnsCodeWhenRoomExists() {
        when(searchRoomCache.searchRoomById(100L)).thenReturn(true);
        when(searchRoomCache.getRoomCode(100L)).thenReturn("ROOM-ABC");

        String result = getPrivateRoomCode.getPrivateRoomCode(100L);

        assertThat(result).isEqualTo("ROOM-ABC");
        verify(searchRoomCache).searchRoomById(100L);
        verify(searchRoomCache).getRoomCode(100L);
    }

    @Test
    void getPrivateRoomCodeReturnsNullWhenRoomDoesNotExist() {
        when(searchRoomCache.searchRoomById(200L)).thenReturn(false);

        String result = getPrivateRoomCode.getPrivateRoomCode(200L);

        assertThat(result).isNull();
        verify(searchRoomCache).searchRoomById(200L);
        verify(searchRoomCache, never()).getRoomCode(anyLong());
    }
}