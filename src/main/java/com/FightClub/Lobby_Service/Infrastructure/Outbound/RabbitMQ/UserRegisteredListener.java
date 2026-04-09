package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.UserRegisteredEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserRegisteredListener {

    private final CreateUserCharacterUseCase createUserCharacterUseCase;

    @RabbitListener(queues = "new.user.queue")
    public void recibirEvento(UserRegisteredEvent event) {

        log.info("Evento recibido: Usuario registrado");
        log.info("UserId: {}", event.getUserId());
        log.info("Username: {}", event.getUsername());

        String uniqueCharacterId = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId)
                .characterName(event.getUsername() + "'s Character")
                .characterLevel(1)
                .characterHp(100)
                .characterATK(10)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId);
            UserCharacter created = createUserCharacterUseCase.createUserCharacter(characterDTO);
            log.info("✅ Character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId, created);
        } catch (Exception e) {
            log.error("❌ ERROR al crear character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }
    }
}
