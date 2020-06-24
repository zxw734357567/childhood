package com.zxw.childhood.passport.common.permission;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxw
 * @date 2020-06-24 16:14
 */
public interface AccessPermission {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
