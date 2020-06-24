package com.zxw.childhood.passport.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zxw
 * @date 2020-06-24 15:38
 */
public class PassportAuthFailureException extends AuthenticationException {
    public PassportAuthFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public PassportAuthFailureException(String msg){
        super(msg);
    }
}
