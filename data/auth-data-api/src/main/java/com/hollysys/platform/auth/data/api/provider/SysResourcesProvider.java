package com.hollysys.platform.auth.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.platform.auth.data.api.entity.SysResources;

import java.util.Set;

public interface SysResourcesProvider extends IService<SysResources> {

    Set<SysResources> getResourcesByUserId(String userId);
}
