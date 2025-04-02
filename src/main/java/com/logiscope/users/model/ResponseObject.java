package com.logiscope.users.model;

public class ResponseObject<T> {

    private String message;
    private T data;

    // Constructor
    public ResponseObject(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
