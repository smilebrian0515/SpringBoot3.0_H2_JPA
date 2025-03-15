package com.example.demo.exception;

public class RootException extends Exception {
    private int code;
    private String resourceKey;

    public RootException() {
    }

    public RootException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
}
