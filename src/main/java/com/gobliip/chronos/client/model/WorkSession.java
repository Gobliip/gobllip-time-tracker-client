package com.gobliip.chronos.client.model;

/**
 * Created by lsamayoa on 9/08/15.
 */
public class WorkSession {

    public enum WorkSessionStatus {
        OPEN, PAUSED, CLOSED
    }

    private Long id;

    private Long trackingId;

    private String owner;

    private WorkSessionStatus status;

    private Long lastLoggedPeriodId;

    private Integer keyboardActionsCount;

    private Integer mouseActionsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Long trackingId) {
        this.trackingId = trackingId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public WorkSessionStatus getStatus() {
        return status;
    }

    public void setStatus(WorkSessionStatus status) {
        this.status = status;
    }

    public Long getLastLoggedPeriodId() {
        return lastLoggedPeriodId;
    }

    public void setLastLoggedPeriodId(Long lastLoggedPeriodId) {
        this.lastLoggedPeriodId = lastLoggedPeriodId;
    }

    public Integer getKeyboardActionsCount() {
        return keyboardActionsCount;
    }

    public void setKeyboardActionsCount(Integer keyboardActionsCount) {
        this.keyboardActionsCount = keyboardActionsCount;
    }

    public Integer getMouseActionsCount() {
        return mouseActionsCount;
    }

    public void setMouseActionsCount(Integer mouseActionsCount) {
        this.mouseActionsCount = mouseActionsCount;
    }
}
