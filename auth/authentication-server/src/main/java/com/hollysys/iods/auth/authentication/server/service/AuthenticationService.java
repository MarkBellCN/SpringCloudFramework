package com.hollysys.iods.auth.authentication.server.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    /**
     * 校验权限
     * @param authRequest
     * @return
     */
    boolean decide(HttpServletRequest authRequest);

}
