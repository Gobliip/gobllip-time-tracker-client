package com.gobliip.chronos.client;

import com.gobliip.chronos.client.retrofit.TrackingsResource;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class TimeTrackerClient {

    private TrackingsResource timeTrackerResource;

    public TimeTrackerClient(TrackingsResource timeTrackerResource) {
        this.timeTrackerResource = timeTrackerResource;
    }
}
