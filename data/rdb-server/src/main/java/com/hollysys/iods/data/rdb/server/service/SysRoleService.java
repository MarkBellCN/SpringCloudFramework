package com.hollysys.iods.data.rdb.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.iods.data.api.entity.SysRole;
import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysRoleProvider;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import com.hollysys.iods.data.rdb.server.mapper.SysRoleMapper;
import com.hollysys.iods.data.rdb.server.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(protocol = "dubbo")
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleProvider {

    @Override
    public List<SysRole> getRoleByUserId(String userId) {
        return this.baseMapper.getRoleByUserId(userId);
    }
}