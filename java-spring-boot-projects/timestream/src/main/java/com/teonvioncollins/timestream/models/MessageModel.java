package com.teonvioncollins.timestream.models;

public class MessageModel {

    private String message;

    private String username;

    private Long sessionId;

    public MessageModel() {}

    public MessageModel(String message, String username, Long sessionId) {
        this.message = message;
        this.username = username;
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
