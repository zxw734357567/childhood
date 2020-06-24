package com.zxw.childhood.passport.common.token;

import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import lombok.Data;

/**
 * @author zxw
 * @date 2020-06-24 16:14
 */
@Data
public class DefaultPassportToken implements PassportToken<PassportAuthentication> {
    private String ip;

    private PassportAuthentication value;

    private String key;

    private long expire = 3600;

    public DefaultPassportToken() {
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public PassportAuthentication getValue() {
        return value;
    }

    @Override
    public String getIp() {
        return ip;
    }
}
