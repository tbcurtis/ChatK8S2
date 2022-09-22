package com.example.learnwebsocketreceiver;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@EnableRabbit
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    public static final String TOPIC_DESTINATION_PREFIX = "/topic";
    public static final String REGISTRY = "/ws";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	
    	registry.enableStompBrokerRelay(TOPIC_DESTINATION_PREFIX)
        .setRelayHost(host)
        .setClientLogin(username)
        .setClientPasscode(password)
        .setSystemLogin(username)
        .setSystemPasscode(password);
    	
    	registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }
}