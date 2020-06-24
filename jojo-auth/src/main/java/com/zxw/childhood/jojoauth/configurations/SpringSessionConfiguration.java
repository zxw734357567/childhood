package com.zxw.childhood.jojoauth.configurations;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxw.childhood.jojoauth.utils.jackson2.AuthJackson2Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.web.jackson2.WebJackson2Module;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zxw
 * @date 2020-05-28 19:51
 */
@Slf4j
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3200, redisFlushMode = RedisFlushMode.ON_SAVE,
        redisNamespace = "vole-session")
public class SpringSessionConfiguration {
    /**
     * 自定义Redis Session序列化
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer(){
        log.info("自定义Redis Session序列化加载成功");
        return valueSerializer();
    }

    /**
     * Json格式来存储（每序列化，适合∂debug）
     * 每个会话"vole-session:session:sessions: XXX"是一个Hash数据结构，可以用Redis HASH相关的命令来查看
     * 查看会话key
     * hgetall vole-session:session:sessions:20e6b651-bd4d-4402-bb00-3400055e36ea
     * 查看该会话里的user1信息
     * HMGET "vole-session:session:sessions:20e6b651-bd4d-4402-bb00-3400055e36ea"   sessionAttr:user1
     * @return RedisSerializer
     */
    private RedisSerializer<Object> valueSerializer() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new CoreJackson2Module());
        mapper.registerModule(new WebJackson2Module());
        mapper.registerModule(new AuthJackson2Module());
        RedisSerializer rs = new GenericJackson2JsonRedisSerializer(mapper);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.disable(MapperFeature.AUTO_DETECT_SETTERS);
        return rs;
    }
}
