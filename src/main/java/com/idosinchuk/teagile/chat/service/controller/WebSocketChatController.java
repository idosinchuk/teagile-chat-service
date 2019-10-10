package com.idosinchuk.teagile.chat.service.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.idosinchuk.teagile.chat.service.dto.WebSocketChatMessageDTO;

@Controller
public class WebSocketChatController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/teagilechat")
	public WebSocketChatMessageDTO sendMessage(@Payload WebSocketChatMessageDTO webSocketChatMessage) {
		return webSocketChatMessage;
	}

	@MessageMapping("/chat.newUser")
	@SendTo("/topic/teagilechat")
	public WebSocketChatMessageDTO newUser(@Payload WebSocketChatMessageDTO webSocketChatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		
		headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		return webSocketChatMessage;
	}
}