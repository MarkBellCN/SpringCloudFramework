package com.hollysys.iods.data.rdb.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.iods.data.api.entity.SysResources;
import com.hollysys.iods.data.api.provider.SysResourcesProvider;
import com.hollysys.iods.data.rdb.server.mapper.SysResourceMapper;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

@Service(protocol = "dubbo")
public class SysResourceService extends ServiceImpl<SysResourceMapper, SysResources> implements SysResourcesProvider {
    @Override
    public List<SysResources> getResourcesByUserId(String userId) {
        return this.baseMapper.getResourcesByUserId(userId);
    }
}
