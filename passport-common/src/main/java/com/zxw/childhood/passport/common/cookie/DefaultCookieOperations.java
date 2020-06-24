package com.zxw.childhood.passport.common.cookie;

import com.zxw.childhood.passport.common.contants.PassportConstants;
import com.zxw.childhood.passport.utils.CookieHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxw
 * @date 2020-06-24 13:53
 */
public class DefaultCookieOperations implements CookieOperations {

    private String cookieName = PassportConstants.DEFAULT_COOKIE_NAME;// "passport-sso"

    private String domain = PassportConstants.DEFAULT_COOKIE_DOMAIN;

    private int cookieMaxage = PassportConstants.DEFAULT_COOKIE_MAXAGE;

    private String cookiePath = PassportConstants.DEFAULT_COOKIE_PATH;

    public void setCookieMaxage(int cookieMaxage) {
        this.cookieMaxage = cookieMaxage;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    @Override
    public String getCookieKey(HttpServletRequest request) {
        Cookie cookie = CookieHelper.findCookieByName(request, cookieName);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    @Override
    public void storeCookie(HttpServletResponse response, String cookieValue) {
        CookieHelper.addCookie(response, domain, cookiePath, cookieName, cookieValue,
                cookieMaxage, true, false);
    }

    @Override
    public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieHelper.clearCookieByName(request,response,cookieName,domain,cookiePath);
    }
}
