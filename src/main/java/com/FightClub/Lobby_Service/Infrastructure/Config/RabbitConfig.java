package com.FightClub.Lobby_Service.Infrastructure.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    public static final String REGISTERED_QUEUE = "user.registered.queue";
    public static final String ROUTING_KEY_REGISTERED = "user.registered";
    public static final String DELETED_QUEUE = "user.deleted.queue";
    public static final String ROUTING_KEY_DELETED = "user.deleted";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue guestRegisteredQueue() {
        return new Queue(GUEST_REGISTERED_QUEUE, true);
    }

    @Bean
    public Queue registeredQueue() {
        return new Queue(REGISTERED_QUEUE, true);
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue(DELETED_QUEUE, true);
    }

    @Bean
    public Binding guestRegisteredBinding(
            Queue guestRegisteredQueue,
            DirectExchange userExchange) {

        return BindingBuilder
                .bind(guestRegisteredQueue)
                .to(userExchange)
                .with(ROUTING_KEY_GUEST_REGISTERED);
    }

    @Bean
    public Binding registeredBinding(
            Queue registeredQueue,
            DirectExchange userExchange) {

        return BindingBuilder
                .bind(registeredQueue)
                .to(userExchange)
                .with(ROUTING_KEY_REGISTERED);
    }

    @Bean
    public Binding deletedBinding(
            Queue deletedQueue,
            DirectExchange userExchange) {

        return BindingBuilder
                .bind(deletedQueue)
                .to(userExchange)
                .with(ROUTING_KEY_DELETED);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}