package org.myddd.security.oauth2.mock.auth;

import java.util.Random;

public class MockAuthentication implements IAuthentication {


    public Long mockUserId = Math.abs(new Random().nextLong());

    private static final String MOCK_USER = "admin";

    @Override
    public boolean isAuthentication() {
        return true;
    }

    @Override
    public Long loginUserId() {
        return mockUserId;
    }
}
