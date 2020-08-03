package org.ganjp.api.core.web.response.error;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class ErrorData implements Serializable {
    private final String code;
    private final String title;
    private final String detail;

    protected ErrorData(ErrorDataBuilder builder) {
        this.code = builder.getCode();
        this.title = builder.getTitle();
        this.detail = builder.getDetail();
    }

    public static ErrorDataBuilder builder() {
        return new ErrorDataBuilder();
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ErrorData)) {
            return false;
        } else {
            ErrorData that = (ErrorData)o;
            return Objects.equals(this.code, that.code) && Objects.equals(this.title, that.title) && Objects.equals(this.detail, that.detail);
        }
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.code, this.title, this.detail});
    }

    public String toString() {
        return "ErrorData{code='" + this.code + '\'' + ", title='" + this.title + '\'' + ", detail='" + this.detail + "\'}";
    }
}