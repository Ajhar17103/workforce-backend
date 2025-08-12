package com.workforce.constant;

import lombok.experimental.UtilityClass;


@UtilityClass
public class SecureConstant {
    public String SERVICE_PATH_NAME = "Service-Path-Name";
    public String PUBLIC_ROLE = "PUBLIC_ROLE";
    public String BEARER_PREFIX = "Bearer ";
    public String MICROSERVICE_CLIENT_ID = "microservice-client";
    public String SCOPE_OPENID = "openid";
    public String GRANT_TYPE_CREDENTIALS = "client_credentials";
    public String BASIC_AUTH_VALUE = "Basic bWljcm9zZXJ2aWNlLWNsaWVudDpzMTVLQHZ3IUJoKTFBdW8=";
    public String SERVICE_TOKEN_CACHE_NAME = "serviceTokenCacheKey";
    public String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
}
