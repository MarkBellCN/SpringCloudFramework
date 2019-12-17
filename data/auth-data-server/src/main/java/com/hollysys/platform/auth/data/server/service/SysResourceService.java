package com.hollysys.platform.auth.data.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.platform.auth.data.api.entity.SysResources;
import com.hollysys.platform.auth.data.api.provider.SysResourcesProvider;
import com.hollysys.platform.auth.data.server.mapper.SysResourceMapper;
import org.apache.dubbo.config.annotation.Service;

import java.util.Set;

@Service(protocol = "dubbo")
public class SysResourceService extends ServiceImpl<SysResourceMapper, SysResources> implements SysResourcesProvider {
    @Override
    public Set<SysResources> getResourcesByUserId(String userId) {
        return this.baseMapper.getResourcesByUserId(userId);
    }
}
