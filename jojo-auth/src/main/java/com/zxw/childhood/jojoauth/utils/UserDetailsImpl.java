package com.zxw.childhood.jojoauth.utils;

import com.zxw.childhood.jojoauth.model.pojo.TRole;
import com.zxw.childhood.jojoauth.model.vo.TUserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 该列的目的是作为认证用户
 * @author zxw
 * @date 2020-05-27 19:24
 */
public class UserDetailsImpl  implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String password;
    private Integer isEnable;

    private List<TRole> tRoles;

    public UserDetailsImpl() {}

    public UserDetailsImpl(TUserVO user) {
        this.userId =user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.isEnable = user.getIsEnable();
        this.tRoles = user.getTRoleList();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (TRole role : tRoles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable==1;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TRole> gettRoles() {
        return tRoles;
    }

    public void settRoles(List<TRole> tRoles) {
        this.tRoles = tRoles;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}
