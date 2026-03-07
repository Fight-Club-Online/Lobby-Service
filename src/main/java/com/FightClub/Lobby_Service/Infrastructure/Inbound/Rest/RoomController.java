package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCreation;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets.RoomServiceWebSocket;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
@AllArgsConstructor
public class RoomController {

    private final CreatePrivateRoomUseCase createPrivateRoomUseCase;
    private final  SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/new-room") // Alguien llama desde el front esto
    public void createRoom(String hostId) {
        Room room = createPrivateRoomUseCase.createPrivateRoom(hostId);
        messagingTemplate.convertAndSendToUser(hostId, "/queue/mi-sala", room); //Enviar un mensaje al creator

        //en el front se debe aceptar la conexion y en websockets ya se suscribe a la sala /game/hostId
    }

    @MessageMapping("/successful-connection")
    public void successfulConnection(String playerId) {
    }

}
