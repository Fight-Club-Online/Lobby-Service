package com.FightClub.Lobby_Service.Infrastructure.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    public static final String ROOM_EXCHANGE = "room.exchange";
    public static final String ROUTING_KEY = "room.initialized";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ROOM_EXCHANGE);
    }

    public static final String QUEUE = "room.queue";
    public static final String ROUTING_KEY_GAME_FINALIZED = "game.finalized";
    public static final String ROUTING_KEY_USER_CONNECTED = "user.connected";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public Binding gameFinalizedBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_GAME_FINALIZED);
    }

    @Bean
    public Binding userConnectedBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_USER_CONNECTED);
    }


    public static final String USER_EXCHANGE = "user.events";
    public static final String GUEST_REGISTERED_QUEUE = "user.guest.registered.queue";
    public static final String ROUTING_KEY_GUEST_REGISTERED = "user.guest.registered";

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue guestRegisteredQueue() {
        return new Queue(GUEST_REGISTERED_QUEUE, true);
    }

    @Bean
    public Binding guestRegisteredBinding(
            Queue guestRegisteredQueue,
            TopicExchange userExchange) {

        return BindingBuilder
                .bind(guestRegisteredQueue)
                .to(userExchange)
                .with(ROUTING_KEY_GUEST_REGISTERED);
    }

}