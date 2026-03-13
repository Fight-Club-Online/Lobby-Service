package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;


import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RoomServiceRedisWriter implements RoomCacheWriter {
    private final RedisTemplate<String, Object> redisTemplate;
    private final UpdateTTL updateTTL;

    @Override
    public void saveRoom(Room room) {
       updateTTL.updateTTL(room);
    }

    @Override
    public Room startRoom(String roomCode) {
        Object roomId =  redisTemplate.opsForValue().get(RedisKeys.ROOM_CODE+roomCode);
        if(roomId == null) throw new RuntimeException("Room not found");
        long roomIdLong = (long) roomId;
        Room r = (Room) redisTemplate.opsForValue().get(RedisKeys.ROOM+roomIdLong);
        if(r == null) throw new RuntimeException("Room not found");
        r.setRoomState(RoomState.PLAYING);
        updateTTL.updateTTL(r);
        return r;
    }


}
