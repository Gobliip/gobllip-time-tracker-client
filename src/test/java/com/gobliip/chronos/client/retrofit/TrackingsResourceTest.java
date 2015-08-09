package com.gobliip.chronos.client.retrofit;

import com.gobliip.chronos.client.model.Tracking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.MockServerRule;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.verify.VerificationTimes;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedString;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by lsamayoa on 14/07/15.
 */
public class TrackingsResourceTest {
    private static final int MOCKSERVER_TIME_TO_BREATH = 5000; //ms
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

    private TrackingsResource trackingsResource;

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(1080, this);

    private MockServerClient mockServer;

    @Before
    public void setTimeTrackerResource(){
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:1080/")
                .setConverter(new GsonConverter(gson))
                .build();

        trackingsResource = restAdapter.create(TrackingsResource.class);
    }

    @Test
    public void test_create() throws InterruptedException {
        HttpRequest getTracking = request()
                .withMethod("POST")
                .withPath("/trackings");
        mockServer
                .when(getTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));
        Tracking tracking = trackingsResource.create(new TypedByteArray(null, new byte[]{}), new TypedString(""));

        assertEquals(new Long(1), tracking.getId());
        assertEquals(Tracking.TrackingStatus.RUNNING, tracking.getStatus());
        assertEquals("admin", tracking.getOwner());
        assertNull(tracking.getEnd());
        assertEquals(Instant.ofEpochSecond(1436922627, 359000000), tracking.getStart().toInstant());

        mockServer.verify(getTracking, VerificationTimes.once());
        Thread.sleep(MOCKSERVER_TIME_TO_BREATH);
    }

    @Test
    public void test_stop() throws InterruptedException {
        HttpRequest stopTracking = request()
                .withMethod("POST")
                .withPath("/trackings/3568/stop");
        mockServer
                .when(stopTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));


        trackingsResource.stop(new Long(3568), new TypedByteArray(null, new byte[]{}), new TypedString(""));

        mockServer.verify(stopTracking, VerificationTimes.once());
        Thread.sleep(MOCKSERVER_TIME_TO_BREATH);
    }

    @Test
    public void test_pause() throws InterruptedException {
        HttpRequest pauseTracking = request()
                .withMethod("POST")
                .withPath("/trackings/568/pause");
        mockServer
                .when(pauseTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        trackingsResource.pause(new Long(568), new TypedByteArray(null, new byte[]{}), new TypedString(""));

        mockServer.verify(pauseTracking, VerificationTimes.once());
        Thread.sleep(MOCKSERVER_TIME_TO_BREATH);
    }

    @Test
    public void test_resume() throws InterruptedException {
        HttpRequest resumeTracking = request()
                .withMethod("POST")
                .withPath("/trackings/678/resume");
        mockServer
                .when(resumeTracking, Times.once())
                .respond(response().withBody(CREATE_TRACKING_RESPONSE));

        trackingsResource.resume(new Long(678), new TypedByteArray(null, new byte[]{}), new TypedString(""));

        mockServer.verify(resumeTracking, VerificationTimes.once());
        Thread.sleep(MOCKSERVER_TIME_TO_BREATH);
    }
}
