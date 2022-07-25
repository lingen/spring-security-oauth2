package org.myddd.security.oauth2.mock.third;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static org.myddd.security.oauth2.mock.GrantType.GRANT_TYPE_MOBILE_CODE;

public class MobileValidCodeTokenGranter extends AbstractLocalTokenGranter{

    private static final String GRANT_TYPE = GRANT_TYPE_MOBILE_CODE;

    private static final String USERNAME_KEY = "mobile";

    public MobileValidCodeTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @Override
    String getUsernameKey() {
        return USERNAME_KEY;
    }

    @Override
    void authentication(Map<String, String> parameters) {
        var validCode = parameters.get("validCode");

        var mockCode =  LocalDateTime.now().format(CURRENT_DAY_FORMAT);
        if(!mockCode.equalsIgnoreCase(validCode))throw new InvalidGrantException("不正确的验证码");
    }


}
