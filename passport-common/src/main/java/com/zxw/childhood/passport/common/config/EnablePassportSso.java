package com.zxw.childhood.passport.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zxw
 * @date 2020-06-24 12:11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnablePassportClient
@EnableConfigurationProperties(PassportSsoProperties.class)
@Import({PassportSsoDefaultConfiguration.class, PassportSsoCustomConfiguration.class})
public @interface EnablePassportSso {
}
