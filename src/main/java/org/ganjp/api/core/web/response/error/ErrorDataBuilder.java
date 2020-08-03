package org.ganjp.api.core.web.response.error;


public class ErrorDataBuilder {
    private String code;
    private String title;
    private String detail;


    public ErrorDataBuilder() {
    }

    public ErrorDataBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ErrorDataBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ErrorDataBuilder setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public ErrorData build() {
        return new ErrorData(this);
    }

    protected String getCode() {
        return this.code;
    }

    protected String getTitle() {
        return this.title;
    }

    protected String getDetail() {
        return this.detail;
    }
}