package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.CreatePrivateRoomUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.GetPrivateRoomCodeUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.GetRoomAvailabilityUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomRestController {

    private final CreatePrivateRoomUseCase createPrivateRoomUseCase;
    private final GetPrivateRoomCodeUseCase getPrivateRoomCodeUseCase;
    private final GetRoomAvailabilityUseCase getRoomAvailabilityUseCase;
    private final PlayerLeaveRoomUseCase playerLeaveRoomUseCase;
    private final RoomResponseMapper roomResponseMapper;

    @PostMapping("/create-private")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Crear una sala privada",
            description = "Crea una sala privada y retorna el estado inicial de la sala"
    )
    public RoomResponseDTO createPrivateRoom(String hostId) {
       return roomResponseMapper.toDTO(createPrivateRoomUseCase.createPrivateRoom(hostId));
    }


    @GetMapping("/code")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Obtener Codigo de la sala privada",
            description = "Se obtiene el codigo de la sala con el id de esta"
    )
    public String getPrivateRoomCode(long roomId) {
        return getPrivateRoomCodeUseCase.getPrivateRoomCode(roomId);
    }


    @GetMapping("/availability")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Obtener Disponibilidad de la sala",
            description = "Se obtiene la disponibilidad de la sala con el codigo de esta"
    )
    public int getRoomAvailability(String roomCode) {
        return getRoomAvailabilityUseCase.getRoomAvailability(roomCode);
    }



    @PostMapping("/leave/{userId}")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Salir de la sala",
            description = "Se sale de la sala con el id de esta"
    )
    public void leaveRoom(@PathVariable String userId){
        playerLeaveRoomUseCase.leaveRoom(userId);
    }
}
