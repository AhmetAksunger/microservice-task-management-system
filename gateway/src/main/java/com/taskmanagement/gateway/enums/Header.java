package com.taskmanagement.gateway.enums;

public enum Header {
    GATEWAY_TOKEN("X-GATEWAY-TOKEN"),
    USER_EMAIL("X-USER-EMAIL");

    private String value;

    Header(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
