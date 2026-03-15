package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ;

import com.FightClub.Lobby_Service.Application.Ports.Output.SendMessageProducer;
import com.FightClub.Lobby_Service.Domain.Model.Room;
import com.FightClub.Lobby_Service.Infrastructure.Config.RabbitConfig;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.DTO.RoomInitializedMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomEventProducer implements SendMessageProducer {
    private final RabbitTemplate rabbitTemplate;
    private final RoomInitializedMapper roomInitializedMapper;

    @Override
    public void sendRoom(Room room) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ROOM_EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                roomInitializedMapper.toDto(room)
        );
    }
}
