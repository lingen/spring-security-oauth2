package org.myddd.security.oauth2.mock.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SpringSecurityAuthentication implements IAuthentication {

    @Override
    public boolean isAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    @Override
    public Long loginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserInfo userDetails = (LoginUserInfo) authentication.getPrincipal();
        return userDetails.userId();
    }
}
