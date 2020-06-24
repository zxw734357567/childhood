package com.zxw.childhood.jojoauth.controllers;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zxw
 * @date 2020-05-30 10:19
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {



    /**
     * 自定义登录页认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/require")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }

    /**
     * 用户信息校验
     *
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        if (authentication != null) {
            return authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 清除Redis中 accesstoken refreshtoken
     *
     * @param accesstoken accesstoken
     * @return true/false
     *//*
    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstants.TOKEN_USER_DETAIL, key = "#accesstoken")
    public R<Boolean> removeToken(String accesstoken) {
        return new R<>(consumerTokenServices.revokeToken(accesstoken));
    }*/
}
