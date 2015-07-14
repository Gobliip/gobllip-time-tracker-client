package com.gobliip.retrofit.support;

import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public interface JWTTokenStore {
    Optional<String> getToken();
    void setToken(String token);
}
