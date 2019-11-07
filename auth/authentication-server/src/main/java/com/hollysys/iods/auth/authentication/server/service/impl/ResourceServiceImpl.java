package com.hollysys.iods.auth.authentication.server.service.impl;

import com.hollysys.iods.auth.authentication.server.service.ResourceService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return null;
    }
}
