package com.FightClub.Lobby_Service.Infrastructure.Outbound.WebSockets;

import com.FightClub.Lobby_Service.Application.Ports.Input.Room.PlayerLeaveRoomUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@AllArgsConstructor
public class WebSocketEventListener {
    private final PlayerLeaveRoomUseCase playerLeaveRoomUseCase;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String roomCode = (String) headerAccessor.getSessionAttributes().get("roomCode");
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");

        if (userId != null && roomCode != null) {
            playerLeaveRoomUseCase.leaveRoom(userId);
        }
    }
}
