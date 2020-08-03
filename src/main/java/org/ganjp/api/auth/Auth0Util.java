package org.ganjp.api.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.ganjp.api.core.web.WebConst.BEARER_TOKEN_TYPE;

@Slf4j
public class Auth0Util {
    private static String TOKEN_KEY = "54f12048-1f56-45c1-a3f1-2c546bc2bb42";
    private static long TOKEN_TIMEOUT = 10 * 60 * 1000;
    private final static String JWT_ISSUER = "lb-fw-gw";

    public static String create(Map<String, Object> header) {
        return create(header, new HashMap<>(0), JWT_ISSUER, TOKEN_TIMEOUT);
    }

    public static String create(Map<String, Object> header, Map<String, String> claims) {
        return create(header, claims, JWT_ISSUER, TOKEN_TIMEOUT);
    }

    public static String create(Map<String, Object> header, long timeout) {
        return create(header, new HashMap<>(0), JWT_ISSUER, timeout);
    }

    public static String create(Map<String, Object> header, Map<String, String> claims, String issuer, long timeout) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
            Date date = new Date(System.currentTimeMillis() + timeout);
            JWTCreator.Builder builder = JWT.create()
                    .withHeader(header)
                    .withIssuer(issuer)
                    .withExpiresAt(date);
            for (String key : claims.keySet()) {
                builder.withClaim(key, claims.get(key));
            }
            token = builder.sign(algorithm);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return token;
    }

    public static DecodedJWT decode(String token) {
        DecodedJWT jwt;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer(JWT_ISSUER)
//                    .acceptLeeway(1)
//                    .acceptExpiresAt(1)
//                    .acceptIssuedAt(1)
//                    .acceptNotBefore(1)
                    .build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return jwt;
    }

    static DecodedJWT validateInternalAccessToken(String accessToken, RSAPublicKey publicKey) {
        final int ACCEPT_10_SECS = 10;
        com.auth0.jwt.JWTVerifier verifier = JWT.require(Algorithm.RSA256(publicKey))
                .withIssuer("SBA-AUTH")
                .withClaim("type", "SBA-IAT")
                .acceptLeeway(ACCEPT_10_SECS)
                .build();
        return verifier.verify(accessToken);
    }

    public static DecodedJWT getDecodedJWT(HttpServletRequest request) {
        final String authorization = getAuthorizationFromHeader(request);
        if (isNoneEmpty(authorization)) {
            String[] authValues = StringUtils.split(authorization, " ");

            if (authValues.length < 2) {
                return null;
            }

            String tokenType = authValues[0];
            if (!BEARER_TOKEN_TYPE.equalsIgnoreCase(tokenType)) {
                return null;
            }

            String accessToken = authValues[1];
            try {
                return JWT.decode(accessToken);
            } catch (JWTDecodeException ex) {
                log.error("Not a valid JWT access token : {}, Error : {}", authorization, ex.getMessage());
                return null;
            }
        }

        return null;
    }

    static String getAuthorizationFromHeader(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
