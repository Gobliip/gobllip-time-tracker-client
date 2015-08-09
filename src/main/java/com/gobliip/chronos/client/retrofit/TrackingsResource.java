package com.gobliip.chronos.client.retrofit;

import com.gobliip.chronos.client.model.Moment;
import com.gobliip.chronos.client.model.Tracking;
import retrofit.http.*;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedString;

import java.util.List;

/**
 * Created by lsamayoa on 21/07/15.
 */
public interface TrackingsResource {

    String BASE_URL = "/trackings";

    @Multipart
    @POST(BASE_URL)
    Tracking create(@Part("attachment") final TypedByteArray attachment,
                    @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/{trackingId}/resume")
    Tracking resume(@Path("trackingId") final Long trackingId,
                    @Part("attachment") final TypedByteArray attachment,
                    @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/{trackingId}/pause")
    Tracking pause(@Path("trackingId") final Long trackingId,
                   @Part("attachment") final TypedByteArray attachment,
                   @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/{trackingId}/stop")
    Tracking stop(@Path("trackingId") final Long trackingId,
                  @Part("attachment") final TypedByteArray attachment,
                  @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/{trackingId}/heartbeat")
    Tracking heartbeat(@Path("trackingId") final Long trackingId,
                  @Part("attachment") final TypedByteArray attachment,
                  @Part("memo") final TypedString memo);


    @GET(BASE_URL + "/{trackingId}/moments")
    List<Moment> getMoments();
}
