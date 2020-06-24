package com.zxw.childhood.passport.common.cookie;

import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author zxw
 * @date 2020-06-24 13:59
 */
public interface PassportCookieServices {

    PassportAuthentication loadAuthentication(String cookieValue) throws AuthenticationException;

    boolean removeAuthentication (String cookieValue) throws AuthenticationException;
}
