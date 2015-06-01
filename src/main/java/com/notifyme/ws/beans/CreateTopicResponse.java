package com.notifyme.ws.beans;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Meir Winston
 */
@ApiModel("Create topic response")
public class CreateTopicResponse {
    private long topitId;

    public CreateTopicResponse(long topitId){
        this.topitId = topitId;
    }

    public long getTopitId() {
        return topitId;
    }

    public void setTopitId(long topitId) {
        this.topitId = topitId;
    }
}
