package com.zxw.childhood.passport.common.handler;

import com.zxw.childhood.passport.common.contants.PassportConstants;
import com.zxw.childhood.passport.common.cookie.CookieOperations;
import com.zxw.childhood.passport.common.tokenstore.PassportTokenStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxw
 * @date 2020-06-24 16:39
 */
public class ServerLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {
    private String logoutServiceParamName;

    private CookieOperations cookieOperations;

    private PassportTokenStore passportTokenStore;

    public void setCookieOperations(CookieOperations cookieOperations) {
        this.cookieOperations = cookieOperations;
    }

    public void setPassportTokenStore(PassportTokenStore passportTokenStore) {
        this.passportTokenStore = passportTokenStore;
    }

    public void setLogoutServiceParamName(String logoutServiceParamName) {
        this.logoutServiceParamName = logoutServiceParamName;
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        doLogout(request, response);
        String logoutService = obtainLogoutService(request);
        if (!StringUtils.isEmpty(logoutService)) {
            return logoutService;
        }
        return super.determineTargetUrl(request, response);
    }

    private String getLogoutServiceParamName() {
        return !StringUtils.isEmpty(logoutServiceParamName) ? logoutServiceParamName : PassportConstants.DEFAULT_SERVICE_PARAM;
    }


    protected String obtainLogoutService(HttpServletRequest request) {

        return request.getParameter(getLogoutServiceParamName());
    }

    protected void doLogout(HttpServletRequest request, HttpServletResponse response) {
        String cookieKey = cookieOperations.getCookieKey(request);
        if (cookieKey != null) {
            if (!passportTokenStore.deleteToken(cookieKey)) {
                logger.info("tokenstore don't remove cookiekey:" + cookieKey);
            }
        }
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.handle(request, response, authentication);
    }
}
