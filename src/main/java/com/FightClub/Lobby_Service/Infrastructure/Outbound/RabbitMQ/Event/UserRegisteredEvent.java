package com.FightClub.Lobby_Service.Infrastructure.Outbound.RabbitMQ.Event;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {
    private String userId;
    private String email;
    private String username;
    private String avatarURL;
    private String role;
    private Instant createdAt;
}
