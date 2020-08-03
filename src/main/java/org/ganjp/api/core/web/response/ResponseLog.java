package org.ganjp.api.core.web.response;

import org.apache.commons.io.IOUtils;
import org.ganjp.api.core.model.ApplicationConfig;
import lombok.Data;
import org.ganjp.api.core.util.HttpUtil;
import org.ganjp.api.core.model.Header;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;
import static org.ganjp.api.core.web.WebConst.CONTENT_TYPES;

@Data
public class ResponseLog {
    private String address;
    private String httpMethod;
    private String contentType;
    private String status;
    private HttpServletResponse response;
    private int contentLength;
    private ContentCachingResponseWrapper responseWrapper;
    private static final String applicationName = ApplicationConfig.getApplicationName();

    public ResponseLog(HttpServletRequest request, HttpServletResponse response) {
        address = HttpUtil.getRequestFullURL(request);
        httpMethod = request.getMethod();
        contentType = defaultString(response.getContentType(), "-");
        status = String.valueOf(response.getStatus());
        this.response = response;
    }

    public ResponseLog(HttpServletRequest request, ContentCachingResponseWrapper responseWrapper) {
        address = HttpUtil.getRequestFullURL(request);
        httpMethod = request.getMethod();
        contentType = defaultString(responseWrapper.getContentType(), "-");
        status = String.valueOf(responseWrapper.getStatus());
        contentLength = responseWrapper.getContentSize();
        this.responseWrapper = responseWrapper;
    }

    public String debugString() throws IOException {
        List<Header> headers = new ArrayList<>();
        String payload = getPayloadToLog(responseWrapper);
        Collection<String> headerNames = responseWrapper.getHeaderNames();
        for(String name : headerNames) {
            String value = responseWrapper.getHeader(name);
            headers.add(new Header(name, value));
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        logBasicAttributes(writer);
        writer.println("Content-Length: " + contentLength);
        writer.println("Headers: ");
        writer.println("{" + join(headers) + "}");
        writer.println("Payload: ");
        writer.println(payload);
        return stringWriter.toString();
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        logBasicAttributes(writer);
        return stringWriter.toString();
    }

    void logBasicAttributes(PrintWriter writer) {
        writer.println("Outgoing response from " + applicationName);
        writer.println("-------------------------------");
        writer.println("Address: " + address);
        writer.println("Http-Method: " + httpMethod);
        writer.println("Response-Code: " + status);
        writer.println("Content-Type: " + contentType);
    }

    public static String getPayloadToLog(ContentCachingResponseWrapper response) throws IOException {
        String payload;
        String contentType = defaultString(response.getContentType(), "-");
        int contentLength = response.getContentSize();
        long count = CONTENT_TYPES.stream().filter(c -> contentType.startsWith(c)).count();
        if (count > 0) {
            payload = IOUtils.toString(response.getContentAsByteArray(), response.getCharacterEncoding());
            payload = defaultIfBlank(payload, "-");
        }
        else {
            payload = (contentLength <= 0) ? "-" : "<payload-not-logged>";
        }
        return payload;
    }
}
