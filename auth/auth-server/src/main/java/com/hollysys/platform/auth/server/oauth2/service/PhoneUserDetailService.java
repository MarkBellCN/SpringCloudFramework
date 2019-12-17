package com.hollysys.platform.auth.server.oauth2.service;

import com.hollysys.platform.auth.data.api.entity.SysUser;
import org.springframework.stereotype.Service;


@Service
public class PhoneUserDetailService extends BaseUserDetailService {

    @Override
    protected SysUser getUser(String phone) {
        return null;
    }
}
