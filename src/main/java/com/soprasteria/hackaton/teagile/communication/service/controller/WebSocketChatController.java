package com.soprasteria.hackaton.teagile.communication.service.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.soprasteria.hackaton.teagile.communication.service.dto.WebSocketChatMessageDTO;

@Controller
public class WebSocketChatController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/javainuse")
	public WebSocketChatMessageDTO sendMessage(@Payload WebSocketChatMessageDTO webSocketChatMessage) {
		return webSocketChatMessage;
	}

	@MessageMapping("/chat.newUser")
	@SendTo("/topic/javainuse")
	public WebSocketChatMessageDTO newUser(@Payload WebSocketChatMessageDTO webSocketChatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		
		headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		return webSocketChatMessage;
	}
}