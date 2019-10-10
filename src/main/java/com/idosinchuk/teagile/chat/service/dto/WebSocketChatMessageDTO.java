package com.idosinchuk.teagile.chat.service.dto;

import lombok.Data;

@Data
public class WebSocketChatMessageDTO {

	private String type;

	private String content;

	private String sender;

}