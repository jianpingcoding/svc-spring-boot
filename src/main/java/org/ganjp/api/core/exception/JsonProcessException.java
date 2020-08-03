package org.ganjp.api.core.exception;

public class JsonProcessException extends RuntimeException {
	public JsonProcessException(String message) {
		super(message);
	}

	public JsonProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonProcessException(Throwable cause) {
		super(cause);
	}
}
