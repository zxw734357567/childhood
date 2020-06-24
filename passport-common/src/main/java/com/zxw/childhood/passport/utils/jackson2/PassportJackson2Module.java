package com.zxw.childhood.passport.utils.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zxw.childhood.passport.common.auth.PassportAuthentication;

/**
 * @author zxw
 * @date 2020-06-24 16:42
 */
public class PassportJackson2Module extends SimpleModule {
    public PassportJackson2Module() {
        super(PassportJackson2Module.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(PassportAuthentication.class,PassportAuthenticationMixin.class);
    }
}
