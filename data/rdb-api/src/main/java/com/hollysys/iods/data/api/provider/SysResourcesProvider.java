package com.hollysys.iods.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.iods.data.api.entity.SysResources;

import java.util.List;

public interface SysResourcesProvider extends IService<SysResources> {

    List<SysResources> getResourcesByUserId(String userId);
}
