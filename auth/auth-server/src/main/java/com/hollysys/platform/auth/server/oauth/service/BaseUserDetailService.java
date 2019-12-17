package com.hollysys.platform.auth.server.oauth.service;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.hollysys.platform.auth.data.api.provider.PmUserProvider;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PmUser pmUser = getUser(username);
        // 返回带有用户权限信息的User
        User user =  new org.springframework.security.core.userdetails.User(
                pmUser.getUsername(),
                pmUser.getPassword(),
                isActive(pmUser.getEnable()), true, true, true, this.convertToAuthorities(pmUser));
        return user;
    }

    protected abstract PmUser getUser(String param) ;

    private boolean isActive(String active){
        return true;
    }

    private List<GrantedAuthority> convertToAuthorities(PmUser pmUser) {
        List<GrantedAuthority> authorities = new ArrayList();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        return authorities;
    }
}
