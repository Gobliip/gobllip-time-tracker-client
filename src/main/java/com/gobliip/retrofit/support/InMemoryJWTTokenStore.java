package com.gobliip.retrofit.support;

import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class InMemoryJWTTokenStore implements JWTTokenStore {

    private String token;

    public InMemoryJWTTokenStore() {
    }

    public InMemoryJWTTokenStore(String token) {
        this.token = token;
    }

    @Override
    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    @Override
    public synchronized void setToken(String token) {
        Assert.hasText(token, "JWT token must not be empty");
        this.token = token;
    }


}
