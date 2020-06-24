package com.zxw.childhood.passport.common.config.server;

import com.zxw.childhood.passport.common.config.EnablePassportClient;
import com.zxw.childhood.passport.common.config.PassportSsoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zxw
 * @date 2020-06-16 20:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnablePassportClient
@EnableConfigurationProperties({PassportSsoProperties.class,PassportServerProperties.class}) //EnableConfigurationProperties,可以将property文件映射掉java类中,并且实例化
@Import({PassportServerConfiguration.class})//引入其他配置的bean文件,有点类似以前的xml中的import标签
public @interface EnablePassportServer {
}
