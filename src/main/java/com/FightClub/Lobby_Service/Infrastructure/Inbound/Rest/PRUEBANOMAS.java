package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;


import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsSpectatorUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PRUEBANOMAS {
    private final RoomWsWriter roomWsWriter;
    private final JoinToPrivateRoomUseCase jointoPrivateRoomUseCase;
    private final JoinAsSpectatorUseCase joinAsSpectatorUseCase;
    private final PlayerLeaveRoomUseCase playerLeaveRoomUseCase;

    @PostMapping("/join")
    public void joinRoom(
            @RequestParam String roomId,
            @RequestParam String guestId
    ) {
        JoinRoomCommandDTO pojo = new JoinRoomCommandDTO(roomId, guestId);
        jointoPrivateRoomUseCase.joinToPrivateRoom(pojo);
    }

    @PostMapping("/leave")
    public void leaveRoom(
            @RequestParam String guestId
    ) {
        playerLeaveRoomUseCase.leaveRoom(guestId);
    }

    @PostMapping("/join/spectator")
    public void leaveRoomSpectator(
            @RequestParam String roomCode,
            @RequestParam String guestId
    ) {
        JoinRoomCommandDTO pojo = new JoinRoomCommandDTO(roomCode, guestId);
        joinAsSpectatorUseCase.joinAsSpectator(pojo);
    }
}
