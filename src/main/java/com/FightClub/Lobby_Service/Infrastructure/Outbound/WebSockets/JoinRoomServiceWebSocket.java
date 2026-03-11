package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Application.Ports.Output.JoinRoomWs;
import com.FightClub.Lobby_Service.Domain.Model.Enums.RoomState;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JoinRoomServiceWebSocket implements JoinRoomWs {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public Room joinRoom(String roomId, String guestId) {

        messagingTemplate.convertAndSend(
                "/room/" + roomId,
                guestId + " ha entrado"
        );
        //Enviar un mensaje a toda la sala
        //Spring pondra un /user al path -> /user/queue/mi-sala

        return Room.builder().roomCode(roomId).hostId("test").roomState(RoomState.WAITING).maxPlayers(3).build();
    }
}
