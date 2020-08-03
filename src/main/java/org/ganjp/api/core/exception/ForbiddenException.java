package org.ganjp.api.core.exception;


public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(AppErrorCode.FORBIDDEN);
    }

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ForbiddenException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
