package org.ganjp.api.core.exception;

import lombok.Getter;

@Getter
public class BusinessException extends AppRuntimeException {
    private ErrorCode errorCode;
    private Throwable cause;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause.getCause());
        this.errorCode = errorCode;
        this.cause = cause;
    }
}
