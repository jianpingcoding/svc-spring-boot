package org.ganjp.api.core.web.response.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ErrorResponseBuilder {
    private Integer status;
    private Collection<ErrorData> errors;
    private String path;
    private String cause;

    public ErrorResponseBuilder() {
    }

    public ErrorResponseBuilder setErrorDatas(Collection<ErrorData> errorDatas) {
        this.errors = errorDatas;
        return this;
    }

    public ErrorResponseBuilder setSingleErrorData(ErrorData errorData) {
        List<ErrorData> errorDatas = new ArrayList();
        errorDatas.add(errorData);
        this.errors = errorDatas;
        return this;
    }

    public ErrorResponseBuilder setStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(this.errors, this.status, this.path);
    }
}