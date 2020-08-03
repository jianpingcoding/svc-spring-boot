package org.ganjp.api.core.exception;

public abstract class AppRuntimeException extends RuntimeException {
	public abstract ErrorCode getErrorCode();

	public AppRuntimeException() {
		super();
	}

	public AppRuntimeException(String message) {
		super(message);
	}

	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppRuntimeException(Throwable cause) {
		super(cause);
	}
}
