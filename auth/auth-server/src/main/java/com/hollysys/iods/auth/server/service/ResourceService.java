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
    @Reference
    private SysResourcesProvider resourcesProvider;

    public Set<SysResources> queryByUsername(String username) {
        return resourcesProvider.getResourcesByUserId(username);
    }
}
