package com.gobliip.chronos.client.model;

/**
 * Created by lsamayoa on 9/08/15.
 */
public class WorkPeriod {

    private Long id;

    private Moment start;

    private Moment end;

    private Integer mouseActionsCount;

    private Integer keyboardActionsCount;

    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moment getStart() {
        return start;
    }

    public void setStart(Moment start) {
        this.start = start;
    }

    public Moment getEnd() {
        return end;
    }

    public void setEnd(Moment end) {
        this.end = end;
    }

    public Integer getMouseActionsCount() {
        return mouseActionsCount;
    }

    public void setMouseActionsCount(Integer mouseActionsCount) {
        this.mouseActionsCount = mouseActionsCount;
    }

    public Integer getKeyboardActionsCount() {
        return keyboardActionsCount;
    }

    public void setKeyboardActionsCount(Integer keyboardActionsCount) {
        this.keyboardActionsCount = keyboardActionsCount;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
