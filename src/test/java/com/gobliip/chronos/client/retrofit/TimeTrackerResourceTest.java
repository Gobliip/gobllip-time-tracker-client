package com.gobliip.chronos.client.retrofit;

import com.gobliip.chronos.client.model.Tracking;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.verify.VerificationTimes;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.junit.Assert.assertEquals;

/**
 * Created by lsamayoa on 14/07/15.
 */
public class TimeTrackerResourceTest {

    private static final String CREATE_TRACKING_RESPONSE= "{" +
            "\"id\":1," +
            "\"owner\":\"admin\"," +
            "\"start\":{" +
                "\"nano\":359000000," +
                "\"epochSecond\":1436922627" +
            "}," +
            "\"end\":null," +
            "\"status\":\"RUNNING\"" +
            "}";

    private ClientAndServer mockServer;

    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
    }

    @After
    public void stopProxy() {
        mockServer.stop();
    }


    @Test
    public void test_create() throws InterruptedException {
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:1080/")
                .setConverter(new GsonConverter(gson))
                .build();

        TimeTrackerResource serviceClient = restAdapter.create(TimeTrackerResource.class);

        HttpRequest getTracking = request()
                .withMethod("GET")
                .withPath("/");
        mockServer
                .when(getTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        Tracking tracking = serviceClient.create();

        assertEquals(new Long(1), tracking.getId());
        assertEquals(Tracking.TrackingStatus.RUNNING, tracking.getStatus());
        assertEquals("admin", tracking.getOwner());
        assertNull(tracking.getEnd());
        assertEquals(Instant.ofEpochSecond(1436922627,359000000), tracking.getStart().toInstant());

        mockServer.verify(getTracking, VerificationTimes.once());
    }
}
