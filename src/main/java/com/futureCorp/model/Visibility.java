package com.futureCorp.model;

public enum Visibility {

    Private("Private"),
    Public("Public");


    private final String value;

    Visibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
