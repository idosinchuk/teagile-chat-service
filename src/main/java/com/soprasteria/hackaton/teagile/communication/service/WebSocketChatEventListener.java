package com.soprasteria.hackaton.teagile.communication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.soprasteria.hackaton.teagile.communication.service.dto.WebSocketChatMessageDTO;

@Component
public class WebSocketChatEventListener {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out.println("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) headerAccessor.getSessionAttributes().get("username");
		
		if (username != null) {
			WebSocketChatMessageDTO chatMessage = new WebSocketChatMessageDTO();
			chatMessage.setType("Leave");
			chatMessage.setSender(username);
			messagingTemplate.convertAndSend("/topic/public", chatMessage);
		}
	}
}