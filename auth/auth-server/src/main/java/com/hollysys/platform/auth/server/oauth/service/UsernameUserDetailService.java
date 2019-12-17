package com.hollysys.platform.auth.server.oauth.service;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.hollysys.platform.auth.data.api.provider.PmUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsernameUserDetailService extends BaseUserDetailService {
    @Reference(check = false,timeout = 3000)
    private PmUserProvider pmUserProvider;

    @Override
    protected PmUser getUser(String username) {
        PmUser pmUser = pmUserProvider.getSysUserByUserName(username);
        if(pmUser == null){
            throw new UsernameNotFoundException("username not found exception"+username);
        }
        return pmUser;
    }
}
