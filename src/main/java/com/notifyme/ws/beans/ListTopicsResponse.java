package com.notifyme.ws.beans;

import com.notifyme.db.entities.Topic;
import com.wordnik.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author Meir Winston
 */
@ApiModel("List topics response")
public class ListTopicsResponse {
    private List<Topic> list;

    public ListTopicsResponse(List<Topic> list){
        this.list = list;
    }

    public List<Topic> getList() {
        return list;
    }

    public void setList(List<Topic> list) {
        this.list = list;
    }
}
