package com.zxw.childhood.jojoauth.utils.jackson2;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
/**
 * @author zxw
 * @date 2020-05-28 19:48
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class",include = JsonTypeInfo.As.PROPERTY)
@JsonDeserialize(using = UserDetailsImplDeserializer.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserDetailsImplMixin {
}
