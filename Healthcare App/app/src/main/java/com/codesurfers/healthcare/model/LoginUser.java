package com.codesurfers.healthcare.model;

public class LoginUser {
    private String responseCode;
    private String responseMessage;
    private User user;

    public LoginUser(String responseCode, String responseMessage, User user) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.user = user;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
