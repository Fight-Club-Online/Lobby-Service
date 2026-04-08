package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.FightClub.Lobby_Service.Domain.Repository.UserCharacterRepository;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event.UserDeletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserDeletedListener {

    private final UserCharacterRepository userCharacterRepository;

    @RabbitListener(queues = "user.deleted.queue")
    public void recibirEvento(UserDeletedEvent event) {

        log.info("Evento recibido: Usuario eliminado");
        log.info("UserId: {}", event.getUserId());
        log.info("Deleted at: {}", event.getDeletedAt());

        try {
            userCharacterRepository.deleteByUserId(event.getUserId());
            log.info(" Todos los characters del usuario {} han sido eliminados correctamente", event.getUserId());
        } catch (Exception e) {
            log.error(" Error al eliminar characters del usuario: {}", event.getUserId(), e);
        }
    }
}
