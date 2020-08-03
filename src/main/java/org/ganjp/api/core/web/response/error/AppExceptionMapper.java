package org.ganjp.api.core.web.response.error;

import io.crnk.core.engine.error.ExceptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ganjp.api.core.util.JsonUtil;
import org.ganjp.api.core.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;

import static org.ganjp.api.core.web.response.HttpStatus.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.raw;
import static org.ganjp.api.core.web.WebConst.*;

@Slf4j
@Component
public class AppExceptionMapper<E extends Throwable> {

    @Autowired(required = false)
    private ErrorCode defaultErrorCode;

    public ErrorResponse toErrorResponse(Exception exception, String requestPath) {
        ErrorCode errorCode = (defaultErrorCode == null) ? AppErrorCode.DEFAULT : defaultErrorCode;
        String httpStatus = valueOf(INTERNAL_SERVER_ERROR_500);
        if (exception instanceof NestedServletException) {
            exception = (Exception) exception.getCause();
        }

        String cause = null;
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            errorCode = businessException.getErrorCode();
            if (exception instanceof UnauthorizedException) {
                httpStatus = valueOf(UNAUTHORIZED_401);
            } else if (exception instanceof ForbiddenException) {
                httpStatus = valueOf(FORBIDDEN_403);
            } else {
                httpStatus = valueOf(BAD_REQUEST_400);
            }
            log.error("Exception occurred : {} : {}", businessException.getClass().getName(), exception.getMessage());
        } else if (isTechnicalExceptionWithCode(exception)) {
            TechnicalException technicalException = (TechnicalException) exception;
            errorCode = technicalException.getErrorCode();
        } else {
            String message = exception.getMessage();
            if (StringUtils.isBlank(message)) {
                message = exception.toString();
            }
            errorCode = ((AppErrorCode) errorCode).updateDescription(message);
            log.error("Exception occurred : " + exception.getMessage(),  exception);
        }

        ErrorDataBuilder errorDataBuilder = ErrorData.builder()
                .setCode(errorCode.getCode())
                .setTitle(errorCode.getTitle())
                .setDetail(errorCode.getDescription());

        ErrorData errorData = errorDataBuilder.build();
        int statusCode = parseInt(httpStatus);
        ErrorResponseBuilder errorResponseBuilder = ErrorResponse.builder()
                .setStatus(statusCode)
                .setSingleErrorData(errorData)
                .setPath(requestPath);

        log.error("{}",
                raw(ERROR_RESPONSE, JsonUtil.toJson(errorData)),
                kv(HTTP_STATUS, statusCode));
        return errorResponseBuilder.build();
    }

    private boolean isTechnicalExceptionWithCode(Exception exception) {
        return exception instanceof TechnicalException && ((TechnicalException) exception).getErrorCode() != null;
    }
}
