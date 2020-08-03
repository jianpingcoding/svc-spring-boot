package org.ganjp.api.core.web;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ganjp.api.auth.Auth0Util;
import org.ganjp.api.core.web.response.error.ErrorResponse;
import org.ganjp.api.core.web.response.error.AppExceptionMapper;
import org.ganjp.api.core.web.request.RequestLog;
import org.ganjp.api.core.web.response.ResponseLog;
import org.ganjp.api.core.web.request.ClonedBodyRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.ganjp.api.core.web.WebConst.*;

@Slf4j
@Priority(LOG_AND_ERROR_HANDLE_FILTER_PRIORITY)
@Component
public class LogAndErrorHandleFilter extends OncePerRequestFilter {

    private ThreadLocalStore threadLocalStore = ThreadLocalStore.getInstance();

    @Autowired
    private AppExceptionMapper appExceptionMapper;

    @Setter
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        Long startTime = System.currentTimeMillis();
        ContentCachingResponseWrapper wrappedResponse = null;

        try {
            populateData(request);

            if (isDebugEnabled()) {
                ClonedBodyRequestWrapper wrappedRequest = new ClonedBodyRequestWrapper(request);
                wrappedResponse = new ContentCachingResponseWrapper(response);
                logRequestOnDebugMode(wrappedRequest);
                filterChain.doFilter(wrappedRequest, wrappedResponse);
                logResponseOnDebugMode(wrappedRequest, wrappedResponse);
            } else {
                logRequest(request);
                filterChain.doFilter(request, response);
                logResponse(request, response);
            }
        } catch (Exception exception) {
            setErrorResponse(request, response, exception);
        } finally {
            String queryString = request.getQueryString();
            log.info("Request time taken : {} {} {} {} ms {}",
                    kv(HTTP_METHOD, request.getMethod()),
                    kv(REQUEST_URI, request.getRequestURI()),
                    (queryString == null) ? "" : kv(QUERY_STRING, queryString),
                    kv(TIME_TAKEN_IN_MILLIS, (System.currentTimeMillis() - startTime)),
                    kv(HTTP_STATUS, response.getStatus()),
                    kv(PERFORMANCE_METRIC, REQUEST_TIME_TAKEN_KEY)
            );

            threadLocalStore.clear();
            if (wrappedResponse != null) {
                wrappedResponse.copyBodyToResponse();
            }
        }
    }

    /**
     * Clear the threadLocalMap and MDC,
     * Add applicationName, requestId, trackId, callerServiceName to threadLocalMap and MDC
     * @param request
     */
    private void populateData(HttpServletRequest request) {
        threadLocalStore.clear();
        threadLocalStore.init();
        populateRequestId(request);
        populateTrackId(request);
        populateCallerServiceName(request);
    }
    private void populateRequestId(HttpServletRequest request) {
        String requestId = request.getHeader(REQUEST_ID);
        if (isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
            log.debug("requestId is not found in header, generating new UUID: {}", requestId);
        }
        threadLocalStore.setRequestId(requestId);
    }
    private void populateTrackId(HttpServletRequest request) {
        String trackingId = null;

        DecodedJWT authTokenJWT = Auth0Util.getDecodedJWT(request);
        if (authTokenJWT != null) {
            Claim claim = authTokenJWT.getClaim(TRACK_ID);
            if (claim != null) {
                trackingId = claim.asString();
                log.debug("trackId is found in JWT token, generating new UUID");
            }
        }

        if (isBlank(trackingId)) {
            trackingId = request.getHeader(TRACK_ID);
        }

        threadLocalStore.setTrackId(trackingId);
    }
    private void populateCallerServiceName(HttpServletRequest request) {
        String callerServiceName = request.getHeader(CALLER_SERVICE_NAME);
        if (isNotBlank(callerServiceName)) {
            threadLocalStore.setCallerServiceName(callerServiceName);
        }
    }

    // ------------------------ Log ----------------------------
    private boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }
    void logRequestOnDebugMode(HttpServletRequest request) throws IOException {
        RequestLog requestLog = new RequestLog(request);
        log.debug(requestLog.debugString());
    }
    void logResponseOnDebugMode(HttpServletRequest request, ContentCachingResponseWrapper responseWrapper) throws IOException {
        ResponseLog responseLog = new ResponseLog(request, responseWrapper);
        log.debug(responseLog.debugString());
    }
    void logRequest(HttpServletRequest request) {
        RequestLog requestLog = new RequestLog(request);
        log.info(requestLog.toString());
    }
    void logResponse(HttpServletRequest request, HttpServletResponse response) {
        ResponseLog responseLog = new ResponseLog(request, response);
        log.info(responseLog.toString());
    }

    private void setErrorResponse(HttpServletRequest request,
                                  HttpServletResponse response, Exception exception) throws IOException {
        ErrorResponse errorResponse = appExceptionMapper.toErrorResponse(exception, request.getRequestURI());
        response.setStatus(errorResponse.getStatus());
        response.setContentType(APPLICATION_JSON_API);

        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream stream = new ByteArrayOutputStream(4096);

        try {
            objectMapper.writeValue(stream, errorResponse);
            outputStream = response.getOutputStream();
            outputStream.write(stream.toByteArray());
            outputStream.flush();
        } catch (Exception ignore) {
            log.info("Ignoring exception in error conversion : {}", ignore.getMessage());
        } finally {
            closeQuietly(stream);
            closeQuietly(outputStream);
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignore) {
                log.info("Ignoring exception in error conversion : {}", ignore.getMessage());
            }
        }
    }
}
