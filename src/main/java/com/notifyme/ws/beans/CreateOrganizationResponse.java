package com.notifyme.ws.beans;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Meir Winston
 */
@ApiModel("Create a new organization")
public class CreateOrganizationResponse {
    private long organizationId;

    public CreateOrganizationResponse(long organizationId){
        this.organizationId = organizationId;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}
