package org.myddd.security.oauth2.mock.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginUserInfo extends UserDetails {

    Long userId();

}
