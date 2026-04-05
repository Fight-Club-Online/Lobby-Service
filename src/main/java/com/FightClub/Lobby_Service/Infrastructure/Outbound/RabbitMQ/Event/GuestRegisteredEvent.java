package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event;

import java.time.Instant;
import lombok.Data;

@Data
public class GuestRegisteredEvent {

    private String userId;
    private String username;
    private Instant createdAt;
    private Instant guestExpiration;

}
