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

    public static final String BASE_URL = "/trackings";

    @GET(BASE_URL)
    @Headers("Accept: application/json")
    Tracking create();

    @POST(BASE_URL + "/{trackingId}/resume")
    @Headers("Accept: application/json")
    Tracking resume(@Path("trackingId") Long trackingId);

    @POST(BASE_URL + "/{trackingId}/pause")
    @Headers("Accept: application/json")
    Tracking pause(@Path("trackingId") Long trackingId);

    @POST(BASE_URL + "/{trackingId}/stop")
    @Headers("Accept: application/json")
    Tracking stop(@Path("trackingId") Long trackingId);


}
