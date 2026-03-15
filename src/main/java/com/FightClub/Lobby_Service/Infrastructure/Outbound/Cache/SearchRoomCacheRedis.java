package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Application.Ports.Output.SearchRoomCache;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SearchRoomCacheRedis implements SearchRoomCache {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Boolean searchRoomByCode(String roomCode) {
        Object roomId =  redisTemplate.opsForValue().get(RedisKeys.ROOM_CODE+roomCode);
        if(roomId == null) return false;
        long roomIdLong = (long) roomId;
        Object r = redisTemplate.opsForValue().get(RedisKeys.ROOM+roomIdLong);
        return r instanceof Room;
    }

    @Override
    public Boolean searchRoomById(long roomId) {
        Object r = redisTemplate.opsForValue().get(RedisKeys.ROOM+String.valueOf(roomId));
        return r instanceof Room;
    }


    @Override
    public String getRoomCode(long roomId) {
        String roomIdString = String.valueOf(roomId);
        Object r = redisTemplate.opsForValue().get(RedisKeys.ROOM+roomIdString);
        if(!(r instanceof Room room)) return null;
        return room.getRoomCode();
    }

    @Override
    public int getRoomAvailability(String roomCode) {
        Object roomId =  redisTemplate.opsForValue().get(RedisKeys.ROOM_CODE+roomCode);
        if(roomId == null) throw new RuntimeException("Room not found");
        long roomIdLong = (long) roomId;
        Room room = (Room) redisTemplate.opsForValue().get(RedisKeys.ROOM+roomIdLong);

        if(room == null) throw new RuntimeException("Room not found");
        return room.getMaxPlayers() - room.getCurrentPlayers();
    }

    @Override
    public Room getRoomById(long roomId) {
        return (Room) redisTemplate.opsForValue().get(RedisKeys.ROOM+String.valueOf(roomId));
    }

    @Override
    public Room getRoomByCode(String roomCode) {
        Object roomId =  redisTemplate.opsForValue().get(RedisKeys.ROOM_CODE+roomCode);
        if(roomId == null) throw new RuntimeException("Room not found");
        long roomIdLong = (long) roomId;
        return (Room) redisTemplate.opsForValue().get(RedisKeys.ROOM+roomIdLong);
    }
}
