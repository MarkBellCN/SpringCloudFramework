package com.hollysys.platform.auth.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.platform.auth.data.api.entity.SysRole;

import java.util.Set;

public interface SysRoleProvider extends IService<SysRole> {

    Set<SysRole> getRoleByUserId(String userId);
}
