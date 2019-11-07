package com.hollysys.iods.auth.authorization.server.service;

import com.hollysys.iods.data.api.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsernameUserDetailService extends BaseUserDetailService {
    @Override
    protected SysUser getUser(String userName) {
        SysUser sysUser = sysUserProvider.getSysUserByUserName(userName);
        if(sysUser == null){
            log.error("找不到该用户，用户名：" + userName);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + userName);
        }
        return sysUser;
    }
}
