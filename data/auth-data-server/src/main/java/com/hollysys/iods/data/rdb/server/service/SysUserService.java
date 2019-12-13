package com.hollysys.iods.data.rdb.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import com.hollysys.iods.data.rdb.server.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(protocol = "dubbo")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements SysUserProvider {
    @Override
    public SysUser getSysUserByUserId(String userId) {
        return this.baseMapper.getSysUserByUserId(userId);
    }
}
