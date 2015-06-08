package com.notifyme.ws.beans;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Meir Winston
 */
@ApiModel("List topics request")
public class ListTopicsRequest {
    private long organizationId;
    private int offset;
    private int count;

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
