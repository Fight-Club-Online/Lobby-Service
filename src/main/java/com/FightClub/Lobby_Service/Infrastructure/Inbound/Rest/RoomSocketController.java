package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.JoinRoomCommandDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinAsSpectatorUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.JoinToPrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Socket.RoomSocketRequestDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class RoomSocketController {

    private final JoinToPrivateRoomUseCase jointoPrivateRoomUseCase;
    private final JoinAsSpectatorUseCase joinAsSpectatorUseCase;
    private final PlayerLeaveRoomUseCase playerLeaveRoomUseCase;
    private final RoomStateMapper roomStateMapper;



    @MessageMapping("/join-room") // Alguien llama desde el front esto
    public RoomStateDTO joinRoom(RoomSocketRequestDTO roomInfo) {
        JoinRoomCommandDTO pojo = new JoinRoomCommandDTO(
                roomInfo.getRoomCode(),
                roomInfo.getUserId()
        );
        return roomStateMapper.toDto(jointoPrivateRoomUseCase.joinToPrivateRoom(pojo));
        //front rest http al restController
        //front se conecta al ws
        //front se suscribe a /room/{roomOD}
        //Un amigo entra entonces front hace send /game/{roomId}
        // el convertAndSend notifiica a toda la sala /room/{roomID}
    }


    @MessageMapping("/join-room/spectator")
    public RoomStateDTO joinRoomAsSpectator(RoomSocketRequestDTO roomInfo){
        JoinRoomCommandDTO pojo = new JoinRoomCommandDTO(
                roomInfo.getRoomCode(),
                roomInfo.getUserId()
        );
        return roomStateMapper.toDto(joinAsSpectatorUseCase.joinAsSpectator(pojo));
    }

    @MessageMapping("/leave-room")
    public void leaveRoom(String userId) {
        playerLeaveRoomUseCase.leaveRoom(userId);
    }



}
