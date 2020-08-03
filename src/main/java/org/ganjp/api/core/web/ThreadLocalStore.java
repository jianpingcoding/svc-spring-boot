package org.ganjp.api.core.web;

import java.util.HashMap;
import java.util.Map;

import org.ganjp.api.core.model.ApplicationConfig;
import org.ganjp.api.core.web.request.RequestContext;
import org.slf4j.MDC;

import static org.ganjp.api.core.web.WebConst.*;

public class ThreadLocalStore {
    private static ThreadLocalStore threadLocalStore = new ThreadLocalStore();
    private static ThreadLocal<Map<String, Object>> threadLocalMap = new InheritableThreadLocal<>();

    static ThreadLocalStore getInstance() {
        return threadLocalStore;
    }

    // clear threadLocalMap and MDC
    void clear() {
        threadLocalMap.remove();
        MDC.clear();
    }

    // Init application name in MDC
    void init() {
        MDC.put(APPLICATION_NAME, ApplicationConfig.getApplicationName());
    }

    void onContextUpdated() {
        updateMDC();
    }

    void setRequestContext(RequestContext requestContext) {
        setValue(REQUEST_CONTEXT, requestContext);
        onContextUpdated();
    }

    private void updateMDC() {
        RequestContext requestContext = this.getRequestContext();
        MDC.put(SESSION_ID, requestContext.getSessionId());
        MDC.put(USER_ID, requestContext.getUserId());
        MDC.put(LANGUAGE, requestContext.getLanguage());
        MDC.put(CLIENT_IP, requestContext.getClientIp());
    }

    RequestContext getRequestContext() {
        RequestContext requestContext = (RequestContext) getValue(REQUEST_CONTEXT);
        if (requestContext == null) {
            requestContext = new RequestContext();
            setRequestContext(requestContext);
        }
        return requestContext;
    }


    // ------------------------ requestId, trackingId, callerServiceName methods ------------------------

    String getRequestId() {
        return (String) getValue(REQUEST_ID);
    }
    void setRequestId(String requestId) {
        setValue(REQUEST_ID, requestId);
    }

    void setCallerServiceName(String callerServiceName) {
        setValue(CALLER_SERVICE_NAME, callerServiceName);
    }
    String getCallerServiceName() {
        return (String) getValue(CALLER_SERVICE_NAME);
    }

    String getTrackId() {
        return (String) getValue(TRACK_ID);
    }
    void setTrackId(String trackId) {
        setValue(TRACK_ID, trackId);
    }


    // ------------------------ ThreadLocalMap set and get value methods ------------------------

    Map<String, Object> getMap() {
        Map<String, Object> objMap = threadLocalMap.get();

        if (objMap == null) {
            objMap = new HashMap<>();
            threadLocalMap.set(objMap);
        }
        return objMap;
    }

    private void setValue(String key, Object value) {
        if (value != null) {
            getMap().put(key, value);
            MDC.put(key, String.valueOf(value));
        }
    }
    private Object getValue(String key) {
        return getMap().get(key);
    }

    private void clearValue(String key) {
        getMap().remove(key);
        MDC.remove(key);
    }
}
