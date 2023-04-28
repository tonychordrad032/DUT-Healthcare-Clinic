package com.codesurfers.healthcare.model;

public class ResponseResult {
    private int responseCode;
    private String responseMessage;
    private Object results;

    public ResponseResult() {
    }

    public ResponseResult(int responseCode, String responseMessage, Object results) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.results = results;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", results=" + results +
                '}';
    }
}
