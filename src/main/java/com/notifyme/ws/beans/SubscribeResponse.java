package com.notifyme.ws.beans;

/**
 * @author Meir Winston
 */
public class SubscribeResponse {
    private long subsrictionId;

    public SubscribeResponse(long subsrictionId){
        this.subsrictionId = subsrictionId;
    }

    public long getSubsrictionId() {
        return subsrictionId;
    }

    public void setSubsrictionId(long subsrictionId) {
        this.subsrictionId = subsrictionId;
    }
}
