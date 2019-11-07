package com.hollysys.iods.auth.authorization.server.service;

import com.hollysys.iods.auth.api.token.BaseUserDetail;
import com.hollysys.iods.data.api.entity.SysResources;
import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysResourceProvider;
import com.hollysys.iods.data.api.provider.SysRoleProvider;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    @Autowired
    protected SysUserProvider sysUserProvider;
    @Autowired
    private SysRoleProvider sysRoleProvider;
    @Autowired
    private SysResourceProvider sysResourceProvider;
    @Autowired
    private RedisTemplate<String, SysRole> roleTemplate;
    @Autowired
    private RedisTemplate<String, SysResources> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SysUser sysUser = getUser(userName);

        List<SysRole> roles = sysRoleProvider.getRoleByUserId(sysUser.getId());

        List<SysResources> resources = sysResourceProvider.getResourcesByUserId(sysUser.getId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(sysUser, roles);

        // 存储菜单到redis
        resourcesTemplate.delete(sysUser.getId() + "-menu");
        resources.forEach(e -> {
            resourcesTemplate.opsForList().leftPush(sysUser.getId() + "-menu", e);
        });

        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(sysUser.getUserName(),
                sysUser.getPassword(), isActive(sysUser.getActive()), true, true, true, authorities);
        return new BaseUserDetail(sysUser, user);
    }

    protected abstract SysUser getUser(String userName) ;

    private boolean isActive(int active){
        return active == 1 ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(SysUser sysUser, List<SysRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        roleTemplate.delete(sysUser.getId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
            authorities.add(authority);
            //存储角色到redis
            roleTemplate.opsForList().rightPush(sysUser.getId(), e);
        });
        return authorities;
    }
}
