package com.FightClub.Lobby_Service.Infrastructure.Config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebSocketConfigTest {

    @InjectMocks
    private WebSocketConfig webSocketConfig;

    @Mock
    private StompEndpointRegistry registry;

    @Mock
    private StompWebSocketEndpointRegistration endpointRegistration;

    @Mock
    private MessageBrokerRegistry brokerRegistry;

    @Test
    void testWebSocketConfigExists() {
        // Assert
        assertThat(webSocketConfig).isNotNull();
    }

    @Test
    void testWebSocketConfigIsWebSocketMessageBrokerConfigurer() {
        // Assert
        assertThat(webSocketConfig).isInstanceOf(org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer.class);
    }

    @Test
    void testWebSocketConfigCanRegisterStompEndpoints() {
        // Arrange & Act & Assert
        when(registry.addEndpoint(anyString())).thenReturn(endpointRegistration);
        when(endpointRegistration.setAllowedOriginPatterns(anyString())).thenReturn(endpointRegistration);
        when(endpointRegistration.withSockJS()).thenReturn(mock(SockJsServiceRegistration.class));

        assertThatCode(() -> webSocketConfig.registerStompEndpoints(registry))
                .doesNotThrowAnyException();
    }

    @Test
    void testWebSocketConfigCanConfigureMessageBroker() {
        // Arrange & Act & Assert
        assertThatCode(() -> webSocketConfig.configureMessageBroker(brokerRegistry))
                .doesNotThrowAnyException();
    }
}
