package com.hollysys.iods.auth.server.service;

import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysResourcesProvider;
import com.hollysys.iods.data.api.provider.SysRoleProvider;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    @Reference
    protected SysUserProvider sysUserProvider;
    @Reference
    private SysRoleProvider sysRoleProvider;
    @Reference
    private SysResourcesProvider sysResourcesProvider;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        SysUser sysUser = getUser(userId);

        // 返回带有用户权限信息的User
        User user =  new org.springframework.security.core.userdetails.User(
                sysUser.getUserName(),
                sysUser.getPassword(),
                isActive(sysUser.getActive()), true, true, true, this.convertToAuthorities(sysUser));
        return user;
    }

    protected abstract SysUser getUser(String userId) ;

    private boolean isActive(Integer active){
        return active == null || active == 1;
    }

    private List<GrantedAuthority> convertToAuthorities(SysUser sysUser) {
        Set<SysRole> roles = sysRoleProvider.getRoleByUserId(sysUser.getUserId());
        List<GrantedAuthority> authorities = new ArrayList();
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(e.getId()));
            authorities.add(authority);
        });
        return authorities;
    }
}
