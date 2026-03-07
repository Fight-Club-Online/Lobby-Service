package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Output.RoomCreation;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets.RoomServiceWebSocket;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
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
        //Spring pondra un /user al path -> /user/queue/mi-sala


        System.out.println("Sala creada: " + room.getRoomCode());
        //en el front se debe aceptar la conexion y en websockets ya se suscribe al path y luego un send a /game/hostId
    }

    @MessageMapping("/successful-connection")
    public void successfulConnection(Map<String, String> payload) {
        String roomId = payload.get("roomId");
        String user = payload.get("user");

        System.out.println("Confirmado: " + user + " ya esta en " + roomId);

        messagingTemplate.convertAndSend("/salas/" + roomId, user + " ha entrado oficialmente.");
    }

}
