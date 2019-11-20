package com.hollysys.iods.auth.server.service;

import com.hollysys.iods.data.api.entity.SysUser;
import org.springframework.stereotype.Service;


@Service
public class PhoneUserDetailService extends BaseUserDetailService {

    @Override
    protected SysUser getUser(String phone) {
        return null;
    }
}
