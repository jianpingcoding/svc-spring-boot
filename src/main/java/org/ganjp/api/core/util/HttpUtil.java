package org.ganjp.api.core.util;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;


import static org.apache.commons.lang3.StringUtils.*;


@Slf4j
public class HttpUtil {

    public static String getRequestFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (isNotEmpty(queryString)) {
            requestURL.append("?").append(queryString);
        }
        return requestURL.toString();
    }

}
