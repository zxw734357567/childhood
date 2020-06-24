package com.zxw.childhood.passport.common.filter;

import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import com.zxw.childhood.passport.common.auth.PassportAuthenticationDetailsSource;
import com.zxw.childhood.passport.common.cookie.CookieOperations;
import com.zxw.childhood.passport.common.cookie.PassportCookieServices;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxw
 * @date 2020-06-24 16:02
 */
public class PassportCookieAuthenticationFilter extends GenericFilterBean {
    private CookieOperations cookieOperations ;

    private PassportCookieServices passportCookieServices;

    public void setCookieOperations(CookieOperations cookieOperations) {
        this.cookieOperations = cookieOperations;
    }

    public void setPassportCookieServices(PassportCookieServices passportCookieServices) {
        this.passportCookieServices = passportCookieServices;
    }

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new PassportAuthenticationDetailsSource();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String cookieKey = cookieOperations.getCookieKey(request);
        if (cookieKey != null) {
            PassportAuthentication authResult = passportCookieServices.loadAuthentication(cookieKey);
            if (authResult != null) {
                authResult.setDetails(authenticationDetailsSource.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        chain.doFilter(request,response);
    }
}
