package org.ganjp.api.core.exception;


public class UsernameFoundException extends BusinessException {

    public UsernameFoundException() {
        super(AppErrorCode.USER_NAME_DUPLICATE);
    }

    public UsernameFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UsernameFoundException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
