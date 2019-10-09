package com.soprasteria.hackaton.teagile.communication.service.dto;

import lombok.Data;

@Data
public class WebSocketChatMessageDTO {

	private String type;

	private String content;

	private String sender;

}