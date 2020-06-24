package com.zxw.childhood.passport.common.tokenstore;

import com.zxw.childhood.passport.common.token.PassportToken;

/**
 * @author zxw
 * @date 2020-06-24 16:20
 */
public interface TokenKeyGenerator {
    String extractKey(PassportToken token);
}
