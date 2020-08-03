package org.ganjp.api.core.web.request;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.ganjp.api.core.model.ApplicationConfig;
import org.ganjp.api.core.util.HttpUtil;
import org.ganjp.api.core.model.Header;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;
import static org.ganjp.api.core.web.WebConst.CALLER_SERVICE_NAME;
import static org.ganjp.api.core.web.WebConst.CONTENT_TYPES;
import static org.ganjp.api.core.util.JsonUtil.toJson;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
public class RequestLog {
	private String address;
	private String httpMethod;
	private String contentType;
	private HttpServletRequest request;
	private static final String applicationName = ApplicationConfig.getApplicationName();

	public RequestLog(HttpServletRequest request) {
		this.request = request;
		address = HttpUtil.getRequestFullURL(request);
		httpMethod = request.getMethod();
		contentType = defaultString(request.getContentType(), "-");
	}

	public String debugString() throws IOException {
		List<Header> headers = new ArrayList<>();
		String payload = getPayloadToLog(request);
		Enumeration<String> headerNames = request.getHeaderNames();
		while (!isEmpty(headerNames) && headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			headers.add(new Header(name, value));
		}
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		logBasicAttributes(writer);
		writer.println("Headers: ");
		writer.println("{" + join(headers) + "}");
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap != null && !parameterMap.isEmpty()) {
			writer.println("Params: ");
			writer.println(toJson(parameterMap));
		}
		writer.println("Payload: ");
		writer.println(payload);
		return stringWriter.toString();
	}

	public String getPayloadToLog(HttpServletRequest request) throws IOException {
		String payload;
		String contentType = defaultString(request.getContentType(), "-");
		int contentLength = request.getContentLength();
		long count = CONTENT_TYPES.stream().filter(c -> contentType.startsWith(c)).count();
		if (count > 0) {
			payload = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			payload = defaultIfBlank(payload, "-");
		} else {
			payload = (contentLength <= 0) ? "-" : "<payload-not-logged>";
		}
		return payload;
	}


	@Override
	public String toString() {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		logBasicAttributes(writer);
		return stringWriter.toString();
	}

	void logBasicAttributes(PrintWriter writer) {
		String callerServiceName = request.getHeader(CALLER_SERVICE_NAME);
		writer.print("Incoming request to " + applicationName);
		if (isNotBlank(callerServiceName)) {
			writer.print(" from " + callerServiceName);
		}
		writer.println();
		writer.println("-------------------------------");
		writer.println("Address: " + address);
		writer.println("Http-Method: " + httpMethod);
		writer.println("Content-Type: " + contentType);
		writer.println("Content-Length: " + request.getContentLength());
	}
}
