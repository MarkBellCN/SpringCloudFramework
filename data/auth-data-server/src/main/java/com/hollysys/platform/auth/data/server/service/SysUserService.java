package com.hollysys.platform.auth.data.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.platform.auth.data.api.entity.SysUser;
import com.hollysys.platform.auth.data.api.provider.SysUserProvider;
import com.hollysys.platform.auth.data.server.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements SysUserProvider {
    @Override
    public SysUser getSysUserByUserId(String userId) {
        return this.baseMapper.getSysUserByUserId(userId);
    }
}
