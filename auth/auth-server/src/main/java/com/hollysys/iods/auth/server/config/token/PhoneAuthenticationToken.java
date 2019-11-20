package com.hollysys.iods.auth.server.config.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneAuthenticationToken extends AuthenticationToken {

    public PhoneAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PhoneAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
