package com.hollysys.platform.auth.server.oauth2.service;

import com.hollysys.platform.auth.data.api.entity.SysUser;
import com.hollysys.platform.auth.data.api.provider.SysResourcesProvider;
import com.hollysys.platform.auth.data.api.provider.SysRoleProvider;
import com.hollysys.platform.auth.data.api.provider.SysUserProvider;
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

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    @Reference(check = false)
    protected SysUserProvider sysUserProvider;
    @Reference(check = false)
    private SysRoleProvider sysRoleProvider;
    @Reference(check = false)
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
        List<GrantedAuthority> authorities = new ArrayList();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        return authorities;
    }
}
