package com.zxw.childhood.jojoauth.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author zxw
 * @date 2020-05-27 19:28
 */
@Data
public class PermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private Integer permissionId;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;



    //重写,如果相同的话就是
    @Override
    public int hashCode() {
        return permissionId.hashCode();
    }



    /**
     * permissionId 相同则相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PermissionVO) {
            Integer targetMenuId = ((PermissionVO) obj).getPermissionId();
            return permissionId.equals(targetMenuId);
        }
        return super.equals(obj);
    }
}
