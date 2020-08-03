package org.ganjp.api.core.exception;

import lombok.Getter;



@Getter
public enum AppErrorCode implements ErrorCode {
    DEFAULT("100", "Error Occurred", "An error occurred. Please contact support with the error ID"),

    CACHE_NOT_FOUND("200", "Cache not found", "Cache with name %s not found"),

    UN_AUTHORIZED("300", "Unauthorized", "Not authorized to access the resource"),
    NO_AUTH_SCHEME("301", "Unauthorized", "Auth Scheme not specified"),

    TOKEN_TYPE_NOT_SUPPORTED("302", "Unauthorized", "Auth token type not supported"),
    INVALID_JWT("303", "Unauthorized", "Not a valid JWT"),

    TOKEN_INVALID("304", "Unauthorized", "Token invalid or expired"),
    TOKEN_NOT_FOUND("305", "Unauthorized", "Token not found"),

    FORBIDDEN("400", "Forbidden", "Forbidden to access the resource"),

    USER_NAME_DUPLICATE("501", "Username Duplicate", "The user name has been existing");


    private String code;
    private String title;
    private String description;

    public String toString() {
        return this.getCode() + " : " + this.getTitle() + " : " + this.getDescription();
    }

    AppErrorCode(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public AppErrorCode updateDescription(String description) {
        this.description = description;
        return this;
    }
}
