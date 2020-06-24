package com.zxw.childhood.passport.common.tokenstore;

import com.zxw.childhood.passport.common.token.PassportToken;

/**
 * @author zxw
 * @date 2020-06-24 16:17
 */
public interface PassportTokenStore {
    public PassportToken storeToken(PassportToken token);

    public boolean deleteToken(PassportToken token);

    public boolean deleteToken(String tokenKey);

    public PassportToken getToken(String tokenKey);

}
