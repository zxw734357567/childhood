package com.zxw.childhood.passport.common.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxw
 * @date 2020-06-24 13:52
 */
public interface CookieOperations {
    public String getCookieKey(HttpServletRequest request);

    public void storeCookie(HttpServletResponse response, String cookieValue);

    public void removeCookie(HttpServletRequest request,HttpServletResponse response);
}
