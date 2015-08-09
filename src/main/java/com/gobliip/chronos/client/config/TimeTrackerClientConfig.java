package com.gobliip.chronos.client.config;

import com.gobliip.chronos.client.TimeTrackerClient;
import com.gobliip.chronos.client.retrofit.TrackingsResource;
import com.gobliip.chronos.client.retrofit.WorkTrackerResource;
import com.gobliip.retrofit.cloud.endpoint.RoundRobinEndpoint;
import com.gobliip.retrofit.cloud.oauth2.jwt.InMemoryJWTTokenStore;
import com.gobliip.retrofit.cloud.oauth2.jwt.JWTTokenStore;
import com.gobliip.retrofit.cloud.oauth2.jwt.OAuthJWTTokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by lsamayoa on 7/14/15.
 */
@Configuration
public class TimeTrackerClientConfig {

    @Value("${gobliip.time-tracker.service-name:time-tracker}")
    private String serviceName;

    @Value("${gobliip.time-tracker.endpoint:http://time-tracker.gobliip.:5022/}")
    private String endpoint;

    @Autowired(required = false)
    private JWTTokenStore jwtTokenStore;

    @Autowired(required = false)
    private OAuthJWTTokenInterceptor jwtTokenInterceptor;

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    @Bean
    public WorkTrackerResource workTrackerResource(){
        return restAdapter().create(WorkTrackerResource.class);
    }

    @Bean
    public TrackingsResource timeTrackerResource(){
        return restAdapter().create(TrackingsResource.class);
    }

    @Bean
    public RestAdapter restAdapter() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson()))
                .setEndpoint(timeTrackerEndpoint())
                .setRequestInterceptor(jwtTokenInterceptor())
                .build();
        return restAdapter;
    }

    @Bean
    public Endpoint timeTrackerEndpoint() {
        // We try to use service discovery first
        if(discoveryClient != null) return new RoundRobinEndpoint(discoveryClient, serviceName);
        return Endpoints.newFixedEndpoint(this.endpoint);
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
