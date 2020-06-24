package com.zxw.childhood.passport.common.auth;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxw
 * @date 2020-06-16 20:14
 */
public class PassportAuthenticationDetails extends WebAuthenticationDetails {
    public PassportAuthenticationDetails(HttpServletRequest request){
        super(request);
    }
}
