package com.futureCorp.model;

public enum ResourceType {

    Link("Link"),
    Document("Document");


    private final String value;

    ResourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
