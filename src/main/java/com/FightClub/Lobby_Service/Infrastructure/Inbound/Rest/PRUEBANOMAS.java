package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;


import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.DTO.JoinRoomPTCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsPlayerTypeUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsSpectatorUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Domain.Model.Enums.PlayerType;
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
    private final JoinAsPlayerTypeUseCase joinAsPlayerTypeUseCase;

    @PostMapping("/join")
    public void joinRoom(
            @RequestParam String roomId,
            @RequestParam String guestId
    ) {
        JoinRoomCommandDTO pojo = new JoinRoomCommandDTO(roomId, guestId);
        jointoPrivateRoomUseCase.joinToPrivateRoom(pojo);
    }

    @PostMapping("/join-2")
    public void joinRoom2(
            @RequestParam String roomId,
            @RequestParam String guestId,
            @RequestParam String playerType
    ) {
        PlayerType playerType2 = PlayerType.valueOf(playerType);
        JoinRoomPTCommandDTO pojo = new JoinRoomPTCommandDTO(roomId, guestId,playerType2);
        joinAsPlayerTypeUseCase.JoinAsPlayerType(pojo);
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
