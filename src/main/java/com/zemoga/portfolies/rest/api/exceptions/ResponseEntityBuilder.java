package com.zemoga.portfolies.rest.api.exceptions;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

	private ResponseEntityBuilder() {
	}

	public static ResponseEntity<Object> build(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
