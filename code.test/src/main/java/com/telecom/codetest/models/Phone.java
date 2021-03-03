package com.telecom.codetest.models;

public class Phone {
    String value;
    Status status;
    public Phone(String value, Status status) {
        this.value = value;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
