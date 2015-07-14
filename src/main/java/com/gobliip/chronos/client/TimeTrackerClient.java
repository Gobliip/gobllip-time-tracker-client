package com.gobliip.chronos.client;

import com.gobliip.chronos.client.retrofit.TimeTrackerResource;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class TimeTrackerClient {

    private TimeTrackerResource timeTrackerResource;

    public TimeTrackerClient(TimeTrackerResource timeTrackerResource) {
        this.timeTrackerResource = timeTrackerResource;
    }
}
