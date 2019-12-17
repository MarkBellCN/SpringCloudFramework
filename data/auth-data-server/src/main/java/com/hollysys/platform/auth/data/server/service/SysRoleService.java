package com.hollysys.platform.auth.data.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.platform.auth.data.api.entity.SysRole;
import com.hollysys.platform.auth.data.api.provider.SysRoleProvider;
import com.hollysys.platform.auth.data.server.mapper.SysRoleMapper;
import org.apache.dubbo.config.annotation.Service;

import java.util.Set;

@Service(protocol = "dubbo")
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleProvider {

    @Override
    public Set<SysRole> getRoleByUserId(String userId) {
        return this.baseMapper.getRoleByUserId(userId);
    }
}
