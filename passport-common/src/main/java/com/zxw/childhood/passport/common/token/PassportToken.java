package com.zxw.childhood.passport.common.token;

import java.io.Serializable;

/**
 * @author zxw
 * @date 2020-06-24 16:15
 */
public interface PassportToken<T> extends Serializable {

    public String getKey();
    public T getValue();
    public String getIp();

}
