package com.hollysys.iods.auth.authorization.server.service;

import com.hollysys.iods.auth.api.token.BaseUserDetail;
import com.hollysys.iods.data.api.entity.SysResources;
import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysResourcesProvider;
import com.hollysys.iods.data.api.provider.SysRoleProvider;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Reference
    protected SysUserProvider sysUserProvider;
    @Reference
    private SysRoleProvider sysRoleProvider;
    @Reference
    private SysResourcesProvider sysResourcesProvider;
    @Autowired
    private RedisTemplate<String, SysRole> roleTemplate;
    @Autowired
    private RedisTemplate<String, SysResources> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        SysUser sysUser = getUser(userId);

        List<SysRole> roles = sysRoleProvider.getRoleByUserId(sysUser.getUserId());

        List<SysResources> resources = sysResourcesProvider.getResourcesByUserId(sysUser.getUserId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(sysUser, roles);

        // 存储菜单到redis
        resourcesTemplate.delete(sysUser.getUserId() + "-menu");
        resources.forEach(e -> {
            resourcesTemplate.opsForList().leftPush(sysUser.getUserId() + "-menu", e);
        });

        // 返回带有用户权限信息的User
        User user =  new org.springframework.security.core.userdetails.User(sysUser.getUserName(),
                sysUser.getPassword(), isActive(sysUser.getActive()), true, true, true, authorities);
        return new BaseUserDetail(sysUser, user);
    }

    protected abstract SysUser getUser(String userId) ;

    private boolean isActive(Integer active){
        return active == null || active == 1;
    }

    private List<GrantedAuthority> convertToAuthorities(SysUser sysUser, List<SysRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        roleTemplate.delete(sysUser.getUserId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(e.getId()));
            authorities.add(authority);
            //存储角色到redis
            roleTemplate.opsForList().rightPush(sysUser.getUserId(), e);
        });
        return authorities;
    }
}
