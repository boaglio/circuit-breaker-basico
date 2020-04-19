package com.boaglio.circuitbreaker.services;

public class ServiceResponse {

    private String status;
    private String message;
    private long   timeElapsed;

    public ServiceResponse() {
    }

    public ServiceResponse(String message, String status, long timeElapsed) {
        this.message = message;
        this.status = status;
        this.timeElapsed = timeElapsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    @Override
    public String toString() {
        return "ServiceResponse [message=" + message + ", status=" + status + ", timeElapsed=" + timeElapsed + "]";
    }

}
