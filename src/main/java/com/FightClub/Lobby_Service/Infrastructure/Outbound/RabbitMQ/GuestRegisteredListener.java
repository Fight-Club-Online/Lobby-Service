package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.GuestRegisteredEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class GuestRegisteredListener {

    private final CreateUserCharacterUseCase createUserCharacterUseCase;

    @RabbitListener(queues = "user.guest.registered.queue")
    public void recibirEvento(GuestRegisteredEvent event) {

        log.info("Evento recibido: Guest registrado");
        log.info("UserId: {}", event.getUserId());

        String uniqueCharacterId = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId)
                .characterName("Guest Hero")
                .characterLevel(1)
                .characterHp(100)
                .characterATK(10)
                .characterDEF(5)
                .build();

        try {
            createUserCharacterUseCase.createUserCharacter(characterDTO);
            log.info("Character creado exitosamente para usuario: {} con ID: {}", event.getUserId(), uniqueCharacterId);
        } catch (Exception e) {
            log.error("Error al crear character para usuario: {}", event.getUserId(), e);
        }
    }
}