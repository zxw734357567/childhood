package com.zxw.childhood.passport.common.config.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zxw
 * @date 2020-06-24 12:09
 */
@Data
@ConfigurationProperties(prefix = "security.passport.server")
public class PassportServerProperties {
    private String loginPage = "/passport/login";

    private String loginProcess = "/passport/form";

    private String logout = "/logout";

    private String logoutSuccess="/passport/login";
}
