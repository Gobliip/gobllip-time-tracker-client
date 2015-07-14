package com.gobliip.chronos.client.retrofit;

import com.gobliip.chronos.client.model.Tracking;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by lsamayoa on 7/14/15.
 */
public interface TimeTrackerResource {

    @GET("/")
    @Headers("Accept: application/json")
    Tracking create();

    @POST("/{trackingId}/resume")
    @Headers("Accept: application/json")
    Tracking resume(@Path("trackingId") Long trackingId);

    @POST("/{trackingId}/pause")
    @Headers("Accept: application/json")
    Tracking pause(@Path("trackingId") Long trackingId);

    @POST("/{trackingId}/stop")
    @Headers("Accept: application/json")
    Tracking stop(@Path("trackingId") Long trackingId);


}
