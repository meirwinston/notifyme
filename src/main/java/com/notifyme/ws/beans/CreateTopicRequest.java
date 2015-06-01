package com.notifyme.ws.beans;

/**
 * @author Meir Winston
 */
public class CreateTopicRequest {
    private String name;
    private long organizationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}
