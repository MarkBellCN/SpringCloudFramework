package com.hollysys.iods.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;

import java.util.List;
import java.util.Set;

public interface SysRoleProvider extends IService<SysRole> {

    Set<SysRole> getRoleByUserId(String userId);
}
