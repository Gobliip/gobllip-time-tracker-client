package com.gobliip.chronos.client.retrofit;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Streaming;

/**
 * Created by lsamayoa on 9/08/15.
 */
public interface AttachmentsResource {

    String BASE_URL = "/attachments";

    @GET(BASE_URL + "/{attachmentId}/raw")
    @Streaming
    Response getRaw(@Path("attachmentId") final Long attachmentId);

    @GET(BASE_URL + "/{attachmentId}/base64")
    @Streaming
    String getBase64Encoded(@Path("attachmentId") final Long attachmentId);
}
