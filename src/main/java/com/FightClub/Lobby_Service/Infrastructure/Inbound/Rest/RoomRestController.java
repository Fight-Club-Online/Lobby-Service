package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.*;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.Rest.RoomResponseMapper;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateDTO;
import com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest.DTO.Room.RoomStateMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomRestController {

    private final CreatePrivateRoomUseCase createPrivateRoomUseCase;
    private final GetPrivateRoomCodeUseCase getPrivateRoomCodeUseCase;
    private final GetRoomAvailabilityUseCase getRoomAvailabilityUseCase;
    private final StartRoomUseCase startRoomUseCase;
    private final RoomResponseMapper roomResponseMapper;
    private final RoomStateMapper roomStateMapper;

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
    public RoomStateDTO getRoomAvailability(String roomCode) {
        return roomStateMapper.toDto(getRoomAvailabilityUseCase.getRoomAvailability(roomCode));
    }


    @PostMapping("/start-fight")
    public RoomResponseDTO startFight(@RequestParam String roomCode) {
        return roomResponseMapper.toDTO(startRoomUseCase.startRoom(roomCode));
    }
}
