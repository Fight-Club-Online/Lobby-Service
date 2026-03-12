package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;


import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RoomServiceRedisWriter implements RoomCacheWriter {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveRoom(Room room) {
        String roomId = String.valueOf(room.getRoomId());
        redisTemplate.opsForValue().set("room:"+roomId, room,10, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("roomCode:"+room.getRoomCode(), roomId, 10, TimeUnit.MINUTES);
    }

    @Override
    public void addPlayerToRoomById(String userId, long roomId) {
        Room r = (Room) redisTemplate.opsForValue().get("room:"+String.valueOf(roomId));

    }

    @Override
    public void addPlayerToRoomByCode(String userId, String roomCode) {
        Room r = (Room) redisTemplate.opsForValue().get("roomCode:"+roomCode);
    }


}
