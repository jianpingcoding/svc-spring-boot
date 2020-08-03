package org.ganjp.api.core.web;

import java.util.List;

import static java.lang.Integer.MIN_VALUE;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.*;

public final class WebConst {
    public static final String APPLICATION_NAME = "applicationName";
    public static final String REQUEST_ID = "requestId";
    public static final String TRACK_ID =  "trackId";
    public static final String CALLER_SERVICE_NAME = "serviceName";

    public static final String REQUEST_CONTEXT = "requestContext";
    public static final String SESSION_ID = "sessionId";
    public static final String USER_ID = "userId";
    public static final String LANGUAGE = "language";
    public static final String CLIENT_IP = "clientIp";


    // Securities
    public static final String BEARER_TOKEN_TYPE = "BEARER";


    // Log
    public static final String HTTP_METHOD = "httpMethod";
    public static final String HTTP_STATUS = "httpStatus";
    public static final String REQUEST_URI = "requestURI";
    public static final String QUERY_STRING = "queryString";
    public static final String TIME_TAKEN_IN_MILLIS = "timeTakenInMillis";
    public static final String ERROR_RESPONSE = "ErrorResponse";
    public static final String METHOD_SIGNATURE = "methodSignature";
    public static final String PERFORMANCE_METRIC = "performanceMetric";
    public static final String REQUEST_TIME_TAKEN_KEY = "RequestTimeTaken";
    public static final String LOG_TIME_TAKEN_KEY = "LogTimeTaken";
    public static final String SOAP_TIME_TAKEN_KEY = "SoapTimeTaken";
    public static final String REST_TIME_TAKEN_KEY = "RestTimeTaken";


    // Filter priorities
    public static final int LOG_AND_ERROR_HANDLE_FILTER_PRIORITY = MIN_VALUE;
    public static final int AUTH_FILTER_PRIORITY = MIN_VALUE + 1;
    public static final int CONTEXT_FILTER_PRIORITY = MIN_VALUE + 2;


    public static final String APPLICATION_JSON_API = "application/vnd.api+json";

    public static final List<String> CONTENT_TYPES = asList(
            APPLICATION_JSON_VALUE,
            APPLICATION_XML_VALUE,
            APPLICATION_JSON_API,
            APPLICATION_FORM_URLENCODED_VALUE,
            TEXT_HTML_VALUE,
            TEXT_MARKDOWN_VALUE,
            TEXT_PLAIN_VALUE,
            TEXT_XML_VALUE
    );
}
