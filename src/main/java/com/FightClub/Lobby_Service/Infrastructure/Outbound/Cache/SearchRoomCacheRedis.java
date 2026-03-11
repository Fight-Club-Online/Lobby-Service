package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchRoomCacheRedis implements SearchRoomCache {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Boolean searchRoom(String roomId) {
        Object r = redisTemplate.opsForValue().get("room:"+roomId);
        return r instanceof Room;
    }
}
