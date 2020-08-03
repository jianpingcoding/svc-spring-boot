package org.ganjp.api.core.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@Getter
@Setter(lombok.AccessLevel.PACKAGE)
public class RequestContext implements Serializable {
    protected String requestId;
    protected String sessionId;
    protected String clientId;
    protected String userId;
    protected String language;

    private String trackingId;

    @Setter(lombok.AccessLevel.PACKAGE)
    protected String clientIp;
}
