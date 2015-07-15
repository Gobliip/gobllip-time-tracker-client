package com.gobliip.chronos.client.config;

import com.gobliip.chronos.client.TimeTrackerClient;
import com.gobliip.chronos.client.retrofit.TimeTrackerResource;
import com.gobliip.retrofit.oauth2.InMemoryJWTTokenStore;
import com.gobliip.retrofit.oauth2.JWTTokenStore;
import com.gobliip.retrofit.oauth2.OAuthJWTTokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by lsamayoa on 7/14/15.
 */
@Configuration
public class TimeTrackerClientConfig {

    @Value("${gobliip.time-tracker.endpoint}")
    private String endpoint;

    @Autowired(required = false)
    private JWTTokenStore jwtTokenStore;

    @Autowired(required = false)
    private OAuthJWTTokenInterceptor jwtTokenInterceptor;

    @Bean
    public TimeTrackerClient timeTrackerClient(){
        return new TimeTrackerClient(timeTrackerResource());
    }

    public TimeTrackerResource timeTrackerResource(){
        return restAdapter().create(TimeTrackerResource.class);
    }

    @Bean
    public RestAdapter restAdapter() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson()))
                .setEndpoint(endpoint)
                .setRequestInterceptor(jwtTokenInterceptor())
                .build();
        return restAdapter;
    }

    @Bean
    public OAuthJWTTokenInterceptor jwtTokenInterceptor() {
        if (jwtTokenInterceptor != null) return jwtTokenInterceptor;
        return new OAuthJWTTokenInterceptor(jwtTokenStore());
    }

    @Bean
    public JWTTokenStore jwtTokenStore(){
        if(jwtTokenStore != null) return jwtTokenStore;
        return new InMemoryJWTTokenStore();
    }

    @Bean
    public Gson gson() {
        final Gson gson = new GsonBuilder().create();
        return gson;
    }

}
