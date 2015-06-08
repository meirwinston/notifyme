package com.notifyme.ws.beans;

/**
 * @author Meir Winston
 */
public class PushNotificationRequest {
    private long topicId;
    private String message;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

