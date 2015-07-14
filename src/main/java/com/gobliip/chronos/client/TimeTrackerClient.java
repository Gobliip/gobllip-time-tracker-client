package com.gobliip.chronos.client;

import com.gobliip.chronos.client.model.Tracking;
import com.gobliip.chronos.client.retrofit.TimeTrackerResource;

import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class TimeTrackerClient {

    private TimeTrackerResource timeTrackerResource;

    public TimeTrackerClient(TimeTrackerResource timeTrackerResource) {
        this.timeTrackerResource = timeTrackerResource;
    }
}
