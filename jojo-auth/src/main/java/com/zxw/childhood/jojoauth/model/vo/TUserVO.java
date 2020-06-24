package com.zxw.childhood.jojoauth.model.vo;

import com.zxw.childhood.jojoauth.model.pojo.TRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zxw
 * @date 2020-06-03 15:53
 */
@Data
public class TUserVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id,业务id,U_23232
     */
    private String userId;

    /**
     * 用户名,登录名字
     */
    private String userName;

    /**
     * 电话号码,可以用作登录
     */
    private String telPhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否可用
     */
    private Integer isEnable;

    /**
     * 创建日期
     */
    private Date gmtCreate;

    /**
     * 修改日期
     */
    private Date gmtUpdate;

    /**
     * 密码
     */
    private String password;

    private List<TRole> tRoleList;

    private static final long serialVersionUID = 1L;
}
