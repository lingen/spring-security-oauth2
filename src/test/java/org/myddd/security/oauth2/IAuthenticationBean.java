package org.myddd.security.oauth2;

import org.myddd.security.oauth2.mock.auth.IAuthentication;
import org.myddd.security.oauth2.mock.auth.SpringSecurityAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IAuthenticationBean {

    @Bean
    public IAuthentication authentication(){
        return new SpringSecurityAuthentication();
    }
}
