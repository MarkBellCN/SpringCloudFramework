package com.hollysys.platform.auth.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.platform.auth.data.api.entity.SysUser;

public interface SysUserProvider extends IService<SysUser> {

    SysUser getSysUserByUserId(String userId);
}
