package com.hollysys.platform.auth.server.oauth2.service;

import com.hollysys.platform.auth.data.api.entity.SysResources;
import com.hollysys.platform.auth.data.api.provider.SysResourcesProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

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
