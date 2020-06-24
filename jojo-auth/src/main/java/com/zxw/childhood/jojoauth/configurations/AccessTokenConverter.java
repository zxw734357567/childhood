package com.zxw.childhood.jojoauth.configurations;

import com.zxw.childhood.jojoauth.cmmon.SecurityConstants;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @author zxw
 * @date 2020-05-25 14:43
 */
public class AccessTokenConverter  extends JwtAccessTokenConverter {
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> representation = (Map<String, Object>) super.convertAccessToken(token, authentication);
        representation.put("license", SecurityConstants.JOJO_LICENSE);
        return representation;
    }
}
