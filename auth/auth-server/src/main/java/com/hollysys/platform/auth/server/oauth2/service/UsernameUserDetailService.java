package com.hollysys.platform.auth.server.oauth2.service;

import com.hollysys.iods.data.api.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsernameUserDetailService extends BaseUserDetailService {
    @Override
    protected SysUser getUser(String userId) {
        SysUser sysUser = sysUserProvider.getSysUserByUserId(userId);
        if(sysUser == null){
            throw new UsernameNotFoundException("username not found exception"+userId);
        }
        return sysUser;
    }
}
