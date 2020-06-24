package com.zxw.childhood.passport.common.cookie;

import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import com.zxw.childhood.passport.common.token.PassportToken;
import com.zxw.childhood.passport.common.tokenstore.PassportTokenStore;
import org.springframework.security.core.AuthenticationException;

/**
 * @author zxw
 * @date 2020-06-24 14:00
 */
public class DefaultPassportCookieServices implements PassportCookieServices {

    private PassportTokenStore passportTokenStore ;

    public void setPassportTokenStore(PassportTokenStore passportTokenStore) {
        this.passportTokenStore = passportTokenStore;
    }

    @Override
    public PassportAuthentication loadAuthentication(String cookieValue) throws AuthenticationException {
        PassportToken token = passportTokenStore.getToken(cookieValue);
        if(token!=null) {
            PassportAuthentication auth = (PassportAuthentication) token.getValue();
            return auth;
        }
        return null;
    }

    @Override
    public boolean removeAuthentication(String cookieValue) throws AuthenticationException {
        return passportTokenStore.deleteToken(cookieValue);
    }
}
