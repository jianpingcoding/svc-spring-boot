package org.ganjp.api.core.exception;

public class UnauthorizedException extends BusinessException {
	public UnauthorizedException() {
		super(AppErrorCode.UN_AUTHORIZED);
	}

	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}

	public UnauthorizedException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
