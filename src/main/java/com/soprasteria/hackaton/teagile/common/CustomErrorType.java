package com.soprasteria.hackaton.teagile.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class CustomErrorType {

	private String errorMessage;

	public CustomErrorType(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@SuppressWarnings({ "rawtypes" })
	public static ResponseEntity returnResponsEntityError(String message) {
		return new ResponseEntity<>(new CustomErrorType("An error occurred: " + message),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}