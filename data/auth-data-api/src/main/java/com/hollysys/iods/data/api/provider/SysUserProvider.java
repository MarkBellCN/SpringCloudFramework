package com.hollysys.iods.data.api.provider;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hollysys.iods.data.api.entity.SysUser;

public interface SysUserProvider extends IService<SysUser> {

    SysUser getSysUserByUserId(String userId);
}
