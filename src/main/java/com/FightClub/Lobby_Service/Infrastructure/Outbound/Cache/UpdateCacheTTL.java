package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Domain.Model.Player;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UpdateCacheTTL implements UpdateTTL{
    private final int ttlRoom = 30;
    private final int ttlPlayer = 60;
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public void updateTTL(Room room) {
        redisTemplate.opsForValue().set(RedisKeys.ROOM+room.getRoomId(), room,ttlRoom, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(RedisKeys.ROOM_CODE+room.getRoomCode(), room.getRoomId(), ttlRoom, TimeUnit.MINUTES);

        for(Player p : room.getPlayers()){
            redisTemplate.opsForValue().set(RedisKeys.PLAYER_ROOM+p.getUserId(), room.getRoomId(), ttlPlayer, TimeUnit.MINUTES);
        }
    }
}
