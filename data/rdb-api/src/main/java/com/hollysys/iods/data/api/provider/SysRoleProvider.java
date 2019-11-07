package com.hollysys.iods.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;

import java.util.List;

public interface SysRoleProvider extends IService<SysRole> {

    List<SysRole> getRoleByUserId(String userId);
}
