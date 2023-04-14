package com.codesurfers.healthcare;

public class Feedback {
    private long feedbackId;

    private String name;
    private String surname;
    private String feedBackType;
    private String message;

    public Feedback(String name, String surname, String feedBackType, String message) {
        this.name = name;
        this.surname = surname;
        this.feedBackType = feedBackType;
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFeedBackType(String feedBackType) {
        this.feedBackType = feedBackType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFeedBackType() {
        return feedBackType;
    }

    public String getMessage() {
        return message;
    }
}
