package com.FightClub.Lobby_Service.Infrastructure.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //Lobby como Productor

    //A q broker enviar (recibe y luego decide a donde enviar)
    public static final String ROOM_EXCHANGE = "room.exchange";
    //Clave q usa el nroker para enviar a la queue
    public static final String ROUTING_KEY = "room.initialized";


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ROOM_EXCHANGE);
    }


    //Lobby como Consumidor

    public static final String QUEUE = "room.queue";
    public static final String ROUTING_KEY_GAME_FINALIZED = "game.finalized";
    public static final String ROUTING_KEY_USER_CONNECTED = "user.connected";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    //Cual quier mensaje con esas keys llegara al exchange y luego  a room.queueu
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

}