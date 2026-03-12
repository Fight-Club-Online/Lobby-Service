package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class PlayerCacheWriterRedis implements PlayerCacheWriter {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void leaveRoom(String userId) {
        String roomId = (String) redisTemplate.opsForValue().get("player:room:"+userId);
        Room r = (Room) redisTemplate.opsForValue().get("room:"+roomId);

        if(r == null) throw new RuntimeException("Room not found");

        r.setCurrentPlayers(r.getCurrentPlayers()-1);
        r.setPlayers(r.getPlayers().stream().filter(p -> !p.getUserId().equals(userId)).toList());

        if (r.getCurrentPlayers() == 0 || r.getHostId().equals(userId)){
            redisTemplate.delete("room:"+roomId);
            redisTemplate.delete("roomCode:"+r.getRoomCode());
            redisTemplate.delete("player:room:"+userId);
        }


        redisTemplate.opsForValue().set("room:"+roomId, r,10, TimeUnit.MINUTES);
    }
}
