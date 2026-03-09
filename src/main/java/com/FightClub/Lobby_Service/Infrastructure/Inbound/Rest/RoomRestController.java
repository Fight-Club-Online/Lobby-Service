package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomRestController {
    private final CreatePrivateRoomUseCase createPrivateRoomUseCase;
    private final RoomResponseMapper roomResponseMapper;

    @PostMapping("/create-private")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Crear una sala privada",
            description = "Crea una sala privada y retorna el estado inicial de la sala"
    )
    public RoomResponseDTO createPrivateRoom(String hostId) {
       return roomResponseMapper.toDTO(createPrivateRoomUseCase.createPrivateRoom(hostId));
    }
}
