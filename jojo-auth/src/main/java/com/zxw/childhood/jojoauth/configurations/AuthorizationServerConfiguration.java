package com.zxw.childhood.jojoauth.configurations;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxw.childhood.jojoauth.cmmon.SecurityConstants;
import com.zxw.childhood.jojoauth.utils.UserDetailsImpl;
import com.zxw.childhood.jojoauth.utils.jackson2.AuthJackson2Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.jackson2.WebJackson2Module;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxw
 * @date 2020-01-09 17:08
 * 1.对于redis，实际生产中肯定不是使用单例模式,必须使用的集群,如果使用集群的话,这些关于token的方法必须重新重写,redis-cluster,可能获取token的方式都会发生变化,这个得注意
 */
@Configuration
@Order(Integer.MIN_VALUE)
@EnableAuthorizationServer
@EnableCaching
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    //主要获取的是Client的,这个主要保证clientId的安全
    @Autowired
    private DataSource dataSource;


    //使用默认的认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;


    //认证个人信息
    @Resource(name ="userDetailServiceImpl")
    private UserDetailsService userDetailService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * tokenstore 定制化处理,不定制化的话,你保存的数据压根就不是使用的json数据把
     *
     * @return TokenStore
     * 1. 如果使用的 redis-cluster 模式请使用 VoleRedisTokenStore   如果使用的是redis的集群模式的话，才能走上
     * VoleRedisTokenStore tokenStore = new VoleRedisTokenStore();
     * tokenStore.setRedisTemplate(redisTemplate);
     */
    @Bean
    public TokenStore redisTokenStore() {
        //VoleRedisTokenStore tokenStore = new VoleRedisTokenStore(redisConnectionFactory);
        //tokenStore.setPrefix(SecurityConstants.VOLE_PREFIX);
        JojoRedisTokenStore tokenStore = new JojoRedisTokenStore();
        tokenStore.setRedisTemplate(redisTemplate(redisConnectionFactory));
        return tokenStore;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new CoreJackson2Module());
        mapper.registerModule(new WebJackson2Module());
        mapper.registerModule(new AuthJackson2Module());
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.disable(MapperFeature.AUTO_DETECT_SETTERS);
        RedisSerializer rs = new GenericJackson2JsonRedisSerializer(mapper);
        //template.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
        template.setDefaultSerializer(rs);
        return template;
    }



    //使用的是自带的tokenstore.可以将数据进行转化
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //token增强,
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        /*endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.MINUTES.toSeconds(10)); //分钟
        endpoints.tokenServices(tokenServices);*/

        endpoints
                .tokenStore(redisTokenStore())
                //.tokenStore(new RedisTokenStore(jedisConnectionFactory))
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }


    /**
     * jwt 生成token 定制化处理
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("license", SecurityConstants.JOJO_LICENSE);
            UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
            if (user != null) {
                additionalInfo.put("userId", user.getUserId());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }




    //JWT如何将加密的签名添加进去,这个可以放在
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        AccessTokenConverter jwtAccessTokenConverter = new AccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SecurityConstants.SIGN_KEY);
        return jwtAccessTokenConverter;
    }

}
