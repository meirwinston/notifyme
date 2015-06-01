package com.notifyme.ws.beans;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Meir Winston
 */
@ApiModel("Create a new ogranization request")
public class CreateOrganizationRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
