package com.hollysys.iods.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.iods.data.api.entity.SysResources;

import java.util.List;
import java.util.Set;

public interface SysResourcesProvider extends IService<SysResources> {

    Set<SysResources> getResourcesByUserId(String userId);
}
