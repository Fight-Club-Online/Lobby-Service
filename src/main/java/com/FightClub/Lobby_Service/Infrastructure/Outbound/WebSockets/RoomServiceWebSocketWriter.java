package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RoomServiceWebSocketWriter implements RoomWsWriter {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void joinRoom(String roomId, String guestId) {

        messagingTemplate.convertAndSend(
                "/room/" + roomId,
                guestId + " ha entrado"
        );
        //Enviar un mensaje a toda la sala
        //Spring pondra un /user al path -> /user/queue/mi-sala
    }

    @Override
    public void leaveRoom(String roomId,String guestId){
        messagingTemplate.convertAndSend(
                "/room/" + roomId,
                guestId + " ha salido"
        );
    }

    @Override
    public void StartGame(Room room) {
        messagingTemplate.convertAndSend(
                "/room/" + room.getRoomId(),
                "Room:" + room.getRoomId()  + " ha iniciado"
        );
    }


}
