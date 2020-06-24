package com.zxw.childhood.passport.common.exception;

import java.util.Map;

/**
 * @author zxw
 * @date 2020-06-24 15:37
 */
public class ClientRedirectRequiredException extends RuntimeException  {

    private final String redirectUri;

    private final Map<String, String> requestParams;

    public ClientRedirectRequiredException(String redirectUri, Map<String, String> requestParams){

        super("A redirect is required to get the passport token");
        this.redirectUri = redirectUri;
        this.requestParams = requestParams;

    }

    public String getRedirectUri() {
        return redirectUri;
    }


    public Map<String, String> getRequestParams() {
        return requestParams;
    }
}
