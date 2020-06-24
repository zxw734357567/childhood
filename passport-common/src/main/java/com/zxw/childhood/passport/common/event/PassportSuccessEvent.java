package com.zxw.childhood.passport.common.event;

import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;

/**
 * @author zxw
 * @date 2020-06-24 15:37
 */

public class PassportSuccessEvent extends AbstractAuthenticationEvent {
    public PassportSuccessEvent(Authentication authentication) {
        super(authentication);
    }
}
