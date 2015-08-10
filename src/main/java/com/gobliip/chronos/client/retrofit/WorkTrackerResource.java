package com.gobliip.chronos.client.retrofit;

import com.gobliip.chronos.client.model.WorkPeriod;
import com.gobliip.chronos.client.model.WorkSession;
import retrofit.http.*;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedString;

import java.util.List;

/**
 * Created by lsamayoa on 7/14/15.
 */
public interface WorkTrackerResource {
    String BASE_URL = "/worktracker";


    @GET(BASE_URL+"/{workSessionId}/periods")
    List<WorkPeriod> getWorkPeriods(@Path("workSessionId") final Long trackingId);

    @Multipart
    @POST(BASE_URL+"/start")
    WorkSession start(@Part("attachment") final TypedByteArray attachment,
                    @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/resume")
    WorkSession resume(@Part("attachment") final TypedByteArray attachment,
                    @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/pause")
    WorkSession pause(@Part("attachment") final TypedByteArray attachment,
                   @Part("mouseActions") final int mouseActions,
                   @Part("keyboardActions") final int keyboardActions,
                   @Part("memo") final TypedString memo);

    @Multipart
    @POST(BASE_URL + "/stop")
    WorkSession stop(@Part("attachment") final TypedByteArray attachment,
                  @Part("mouseActions") final int mouseActions,
                  @Part("keyboardActions") final int keyboardActions,
                  @Part("memo") final TypedString memo);
    @Multipart
    @POST(BASE_URL + "/log")
    WorkSession log(@Part("attachment") final TypedByteArray attachment,
                   @Part("mouseActions") final int mouseActions,
                   @Part("keyboardActions") final int keyboardActions,
                   @Part("memo") final TypedString memo);
}
