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

    private TimeTrackerResource timeTrackerResource;

    private ClientAndServer mockServer;

    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
    }

    @After
    public void stopProxy() {
        mockServer.stop();
    }

    @Before
    public void setTimeTrackerResource(){
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:1080/")
                .setConverter(new GsonConverter(gson))
                .build();

        timeTrackerResource = restAdapter.create(TimeTrackerResource.class);
    }

    @Test
    public void test_create() throws InterruptedException {
        HttpRequest getTracking = request()
                .withMethod("GET")
                .withPath("/trackings");
        mockServer
                .when(getTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        Tracking tracking = timeTrackerResource.create();

        assertEquals(new Long(1), tracking.getId());
        assertEquals(Tracking.TrackingStatus.RUNNING, tracking.getStatus());
        assertEquals("admin", tracking.getOwner());
        assertNull(tracking.getEnd());
        assertEquals(Instant.ofEpochSecond(1436922627, 359000000), tracking.getStart().toInstant());

        mockServer.verify(getTracking, VerificationTimes.once());
    }

    @Test
    public void test_stop() throws InterruptedException {
        HttpRequest stopTracking = request()
                .withMethod("POST")
                .withPath("/trackings/3568/stop");
        mockServer
                .when(stopTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        timeTrackerResource.stop(new Long(3568));

        mockServer.verify(stopTracking, VerificationTimes.once());
    }

    @Test
    public void test_pause() throws InterruptedException {
        HttpRequest pauseTracking = request()
                .withMethod("POST")
                .withPath("/trackings/568/pause");
        mockServer
                .when(pauseTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        timeTrackerResource.pause(new Long(568));

        mockServer.verify(pauseTracking, VerificationTimes.once());
    }

    @Test
    public void test_resume() throws InterruptedException {
        HttpRequest resumeTracking = request()
                .withMethod("POST")
                .withPath("/trackings/678/resume");
        mockServer
                .when(resumeTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        timeTrackerResource.resume(new Long(678));

        mockServer.verify(resumeTracking, VerificationTimes.once());
    }
}
