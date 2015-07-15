package com.gobliip.chronos.client.model;



/**
 * Created by lsamayoa on 7/14/15.
 */
public class Tracking {

    public enum TrackingStatus {
        ISSUED, RUNNING, PAUSED, STOPPED
    }

    private Long id;

    private String owner;

    private Instant start;

    private Instant end;

    private TrackingStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public TrackingStatus getStatus() {
        return status;
    }

    public void setStatus(TrackingStatus status) {
        this.status = status;
    }

}
