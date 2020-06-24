package com.zxw.childhood.passport.common.config;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zxw
 * @date 2020-06-24 12:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PassportClientConfiguration.class)
public @interface EnablePassportClient {
}
