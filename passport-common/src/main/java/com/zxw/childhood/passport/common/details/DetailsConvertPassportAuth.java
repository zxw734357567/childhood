package com.zxw.childhood.passport.common.details;

import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zxw
 * @date 2020-06-24 14:11
 */
public class DetailsConvertPassportAuth {
    public static PassportAuthentication convert(PassportUserDetails details){
        Collection<? extends GrantedAuthority> authoritys =details.getAuthorities();
        PassportAuthentication passportAuthentication  = new PassportAuthentication(authoritys);
        passportAuthentication.setUserId(details.getUserId());
        passportAuthentication.setPrincipal(details.getUsername());
        return passportAuthentication;
    }
}
