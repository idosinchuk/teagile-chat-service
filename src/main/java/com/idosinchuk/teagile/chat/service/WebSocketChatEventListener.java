package com.idosinchuk.teagile.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.idosinchuk.teagile.chat.service.dto.WebSocketChatMessageDTO;

// This class listens to events such as a new user joining the chat or an user leaving the chat.
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