package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Application.Ports.Output.PlayerCacheWriter;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
import com.FightClub.Lobby_Service.Domain.Model.Player;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class PlayerCacheWriterRedis implements PlayerCacheWriter {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Room leaveRoom(String userId) {
        String roomId = (String) redisTemplate.opsForValue().get("player:room:"+userId);
        Room r = (Room) redisTemplate.opsForValue().get("room:"+roomId);

        if(r == null) throw new RuntimeException("Room not found"+roomId);

        r.setCurrentPlayers(r.getCurrentPlayers()-1);
        r.setPlayers(r.getPlayers().stream().filter(p -> !p.getUserId().equals(userId)).toList());

        if (r.getCurrentPlayers() == 0 || r.getHostId().equals(userId)){
            redisTemplate.delete("room:"+roomId);
            redisTemplate.delete("roomCode:"+r.getRoomCode());
            redisTemplate.delete("player:room:"+userId);
        }

        redisTemplate.opsForValue().set("room:"+roomId, r,10, TimeUnit.MINUTES);
        return r;
    }


    @Override
    public Room addPlayerToRoom(String userId, long roomId){
        Room r = getRoom(roomId);
        verifyPlayer(r, userId);

        r.setCurrentPlayers(r.getCurrentPlayers()+1);
        Player player = Player.builder().userId(userId).roomCode(r.getRoomCode()).playerType(PlayerType.PLAYER).build();

        addPlayerToRoom(r, player);

        redisTemplate.opsForValue().set("room:"+String.valueOf(roomId), r,10, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("player:room:"+userId, String.valueOf(roomId),10, TimeUnit.MINUTES);
        return r;
    }

    @Override
    public Room addPlayerToRoomByRole(String userId, long roomId, PlayerType role){
        if(role.equals(PlayerType.PLAYER)){
            addPlayerToRoom(userId, roomId);
        }

        Room r = getRoom(roomId);
        verifyPlayer(r, userId);

        r.setCurrentSpectators(r.getCurrentSpectators()+1);
        Player player = Player.builder().userId(userId).roomCode(r.getRoomCode()).playerType(role).build();

        addPlayerToRoom(r, player);

        redisTemplate.opsForValue().set("room:"+String.valueOf(roomId), r,10, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("player:room:"+userId, String.valueOf(roomId),10, TimeUnit.MINUTES);
        return r;
    }

    private void addPlayerToRoom(Room r, Player p){
        List<Player> players = r.getPlayers();
        players.add(p);
        r.setPlayers(players);
    }

    private void verifyPlayer(Room r, String userId){
        if(r.getCurrentPlayers() >= r.getMaxPlayers()) throw new RuntimeException("Room is full");
        Object player = redisTemplate.opsForValue().get("player:room:"+userId);
        if(player != null) throw new RuntimeException("User already in room");
    }

    private Room getRoom(long roomId){
        Room r = (Room) redisTemplate.opsForValue().get("room:"+String.valueOf(roomId));
        if(r == null) throw new RuntimeException("Room not found");
        return r;
    }

}
