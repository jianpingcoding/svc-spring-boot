package org.ganjp.api.core.exception;

import lombok.Getter;

@Getter
public class TechnicalException extends AppRuntimeException {
    private ErrorCode errorCode;
    private Throwable cause;

    public TechnicalException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public TechnicalException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public TechnicalException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TechnicalException(Throwable cause) {
        super(cause.getMessage());
        this.errorCode = AppErrorCode.DEFAULT;
        this.cause = cause;
    }

    public TechnicalException(String message) {
        super(message);
        this.errorCode = AppErrorCode.DEFAULT;
    }

    public TechnicalException(String message, Throwable cause) {
        super(message);
        this.errorCode = AppErrorCode.DEFAULT;
        this.cause = cause;
    }
}
