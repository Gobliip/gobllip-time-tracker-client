package com.gobliip.chronos.client.model;

/**
 * Created by lsamayoa on 14/07/15.
 */
public class Instant {
    private long epochSecond;
    private long nano;

    public Instant() {
    }

    public Instant(long epochSecond, long nano) {
        this.epochSecond = epochSecond;
        this.nano = nano;
    }

    public long getEpochSecond() {
        return epochSecond;
    }

    public void setEpochSecond(long epochSecond) {
        this.epochSecond = epochSecond;
    }

    public long getNano() {
        return nano;
    }

    public void setNano(long nano) {
        this.nano = nano;
    }

    public java.time.Instant toInstant(){
        return java.time.Instant.ofEpochSecond(epochSecond, nano);
    }
}
