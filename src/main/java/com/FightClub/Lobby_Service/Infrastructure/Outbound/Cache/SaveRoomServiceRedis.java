package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;


import com.FightClub.Lobby_Service.Application.Ports.Output.SaveRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class SaveRoomServiceRedis implements SaveRoomCache {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveRoom(Room room) {
        String roomId = String.valueOf(room.getRoomId());
        redisTemplate.opsForValue().set("room:"+roomId, room,10, TimeUnit.MINUTES);
    }
}
