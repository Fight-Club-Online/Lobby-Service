package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Application.Ports.Output.RoomWsWriter;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RoomServiceWebSocketWriter implements RoomWsWriter {

    private final SimpMessagingTemplate messagingTemplate;
    private final RoomStateMapper roomStateMapper;

    @Override
    public void joinRoom(String roomId, Room room) {

        messagingTemplate.convertAndSend(
                "/room/" + roomId,
                roomStateMapper.toDto(room)
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


}
