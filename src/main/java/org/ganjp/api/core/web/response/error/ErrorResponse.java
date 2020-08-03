package org.ganjp.api.core.web.response.error;

import org.slf4j.MDC;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static org.ganjp.api.core.web.WebConst.REQUEST_ID;

public final class ErrorResponse {
    private final String id;
    private final Timestamp timestamp;
    private final Integer status;
    private final Collection<ErrorData> errors;
    private final String path;


    public ErrorResponse(Collection<ErrorData> errorDatas, Integer httpStatus, String path) {
        this.id = MDC.get(REQUEST_ID);
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.errors = errorDatas;
        this.status = httpStatus;
        this.path = path;
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public String getId() {
        return this.id;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Collection<ErrorData> getErrors() {
        return (Collection)(this.errors == null ? Collections.emptyList() : this.errors);
    }

    public String getPath() {
        return this.path;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ErrorResponse)) {
            return false;
        } else {
            ErrorResponse that = (ErrorResponse)o;
            return Objects.equals(this.status, that.status) && Objects.equals(this.errors, that.errors)
                    && Objects.equals(this.path, that.path);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.errors, this.status, this.path});
    }
}

