package com.hollysys.platform.auth.server.service;

import com.hollysys.iods.data.api.entity.SysResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
@Service
@Slf4j
public class AuthenticationService {
    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private ResourceService resourceService;

    public boolean decide(HttpServletRequest authRequest) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<SysResources> userResources = findResourcesByUsername(authentication.getName());
        //用户拥有权限资源 与 url要求的资源进行对比
        return true;
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     */
    public boolean isMatch(Set<SysResources> userResources,String url) {
        return userResources.stream().anyMatch(resource -> resource.getId().equals(url));
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     */
    private Set<SysResources> findResourcesByUsername(String username) {
        //用户被授予的角色资源
        Set<SysResources> resources = resourceService.queryByUsername(username);
        if (log.isDebugEnabled()) {
            log.debug("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", resources.size(), resources);
        }
        return resources;
    }
}
