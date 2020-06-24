package com.zxw.childhood.jojoauth.utils.jackson2;

import com.fasterxml.jackson.annotation.*;

/**
 * @author zxw
 * @date 2020-06-01 16:42
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AuthSavedCookieMixin {
    @JsonCreator
    public AuthSavedCookieMixin(@JsonProperty("name") String name, @JsonProperty("value") String value,
                            @JsonProperty("comment") String comment, @JsonProperty("domain") String domain,
                            @JsonProperty("maxAge") int maxAge, @JsonProperty("path") String path,
                            @JsonProperty("secure") boolean secure, @JsonProperty("version") int version) {

    }
}
