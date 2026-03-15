package com.FightClub.Lobby_Service.Infrastructure.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry registry) {
        registry.addEndpoint("/lobbyFight")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/room","/queue"); // Sv le habla al cliente todo para escuchar ; Estoy suscrito a la room x ; queue -> personal
        // /room/{codeRom}
        registry.setApplicationDestinationPrefixes("/game"); // cliente -> sv (CLiente envia info al websocket) -> todo para hablar
        registry.setUserDestinationPrefix("/user");
    }

}
