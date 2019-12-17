package com.hollysys.platform.auth.server.oauth.service;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import org.springframework.stereotype.Service;


@Service
public class PhoneUserDetailService extends BaseUserDetailService {

    @Override
    protected PmUser getUser(String phone) {
        return null;
    }
}
