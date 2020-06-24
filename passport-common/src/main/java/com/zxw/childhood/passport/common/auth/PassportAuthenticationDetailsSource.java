package com.zxw.childhood.passport.common.auth;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxw
 * @date 2020-06-16 20:20
 */
public class PassportAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    @Override
    public PassportAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new PassportAuthenticationDetails(context);
    }
}
