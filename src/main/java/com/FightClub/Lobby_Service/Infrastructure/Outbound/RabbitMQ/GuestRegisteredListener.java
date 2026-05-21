package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.GuestRegisteredEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class GuestRegisteredListener {

    private final CreateUserCharacterUseCase createUserCharacterUseCase;

    @RabbitListener(queues = "new.guest.queue")
    public void recibirEvento(GuestRegisteredEvent event) {

        log.info("Evento recibido: Guest registrado");
        log.info("UserId: {}", event.getUserId());

        String uniqueCharacterId = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId)
                .characterName("samurai")
                .characterLevel(1)
                .characterHp(105)
                .characterATK(10)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId);
            UserCharacter created = createUserCharacterUseCase.createUserCharacter(characterDTO);
            log.info(" Character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId, created);
        } catch (Exception e) {
            log.error(" ERROR al crear character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }

        String uniqueCharacterId2 = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO2 = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId2)
                .characterName("demonio")
                .characterLevel(1)
                .characterHp(95)
                .characterATK(15)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear segundo character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId2);
            UserCharacter created2 = createUserCharacterUseCase.createUserCharacter(characterDTO2);
            log.info(" Segundo character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId2, created2);
        } catch (Exception e) {
            log.error(" ERROR al crear segundo character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }

        String uniqueCharacterId3 = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO3 = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId3)
                .characterName("caballero")
                .characterLevel(1)
                .characterHp(110)
                .characterATK(15)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear tercer character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId3);
            UserCharacter created3 = createUserCharacterUseCase.createUserCharacter(characterDTO3);
            log.info(" Tercer character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId3, created3);
        } catch (Exception e) {
            log.error(" ERROR al crear tercer character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }

        String uniqueCharacterId4 = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO4 = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId4)
                .characterName("golem")
                .characterLevel(1)
                .characterHp(120)
                .characterATK(15)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear cuarto character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId4);
            UserCharacter created4 = createUserCharacterUseCase.createUserCharacter(characterDTO4);
            log.info(" Cuarto character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId4, created4);
        } catch (Exception e) {
            log.error(" ERROR al crear cuarto character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }

        String uniqueCharacterId5 = UUID.randomUUID().toString();

        CreateUserCharacterDTO characterDTO5 = CreateUserCharacterDTO.builder()
                .userId(event.getUserId())
                .characterId(uniqueCharacterId5)
                .characterName("esqueleto")
                .characterLevel(1)
                .characterHp(90)
                .characterATK(20)
                .characterDEF(5)
                .build();

        try {
            log.info("Intentando crear quinto character para userId: {} con characterId: {}", event.getUserId(), uniqueCharacterId5);
            UserCharacter created5 = createUserCharacterUseCase.createUserCharacter(characterDTO5);
            log.info(" Quinto character creado exitosamente para usuario: {} con ID: {}, resultado: {}", event.getUserId(), uniqueCharacterId5, created5);
        } catch (Exception e) {
            log.error(" ERROR al crear quinto character para usuario: {} - Mensaje: {} - Tipo: {}", 
                event.getUserId(), e.getMessage(), e.getClass().getSimpleName(), e);
        }
    }
}