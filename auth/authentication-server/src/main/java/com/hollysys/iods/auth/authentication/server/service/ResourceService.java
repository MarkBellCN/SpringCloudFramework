package com.hollysys.iods.auth.authentication.server.service;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

public interface ResourceService {

    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest);
}
