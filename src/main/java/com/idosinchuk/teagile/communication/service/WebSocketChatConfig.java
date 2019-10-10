package com.idosinchuk.teagile.communication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Tells that it is a Spring configuration class
@Configuration
// Enables WebSocket message handling, backed by a message broker
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {
	
	@Value("${stomp.relay.host}")
	private String localhost;
	
	@Value("${spring.rabbitmq.username}")
	private String username;
	
	@Value("${spring.rabbitmq.password}")
	private String password;
	
	@Value("${stomp.relay.port}")
	private int relayPort;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocketApp").withSockJS();
	}

	/*
	 * The method configureMessageBroker() enables a rabbitmq message broker to
	 * carry the messages back to the client on destinations prefixed with “/topic”
	 * and “/queue”. Also here we have configured that all messages with “/app”
	 * prefix will be routed to @MessageMapping-annotated methods in controller
	 * class.
	 * 
	 * For example “/app/chat.sendMessage” is the endpoint that the
	 * WebSocketController.sendMessage() method is mapped to handle.
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableStompBrokerRelay("/topic").setRelayHost(localhost).setRelayPort(relayPort).setClientLogin(username)
				.setClientPasscode(password);
	}
}