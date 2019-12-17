package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.hollysys.platform.auth.data.server.mapper.PmUserMapper;
import com.hollysys.platform.auth.data.api.provider.PmUserProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmUserService extends ServiceImpl<PmUserMapper, PmUser> implements PmUserProvider {

    @Override
    public PmUser getSysUserByUserName(String username) {
        return this.baseMapper.getSysUserByUserName(username);
    }
}
