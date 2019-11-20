package com.hollysys.iods.auth.server.service;

import com.hollysys.iods.data.api.entity.SysResources;
import com.hollysys.iods.data.api.provider.SysResourcesProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class ResourceService {
    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Reference
    private SysResourcesProvider resourcesProvider;

    /**
     * 系统中所有权限集合
     */
    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    public Set<SysResources> queryByUsername(String username) {
        return resourcesProvider.getResourcesByUserId(username);
    }

    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return this.resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> this.resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }

    public void removeResource(SysResources resource) {
        resourceConfigAttributes.remove(this.newMvcRequestMatcher(resource.getUrl(), resource.getResourceType()));
        log.info("resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
    }

    public void saveResource(SysResources resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getResourceType()),
                new SecurityConfig(String.valueOf(resource.getId()))
        );
        log.info("resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
    }
}
