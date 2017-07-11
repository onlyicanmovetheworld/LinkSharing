package com.futureCorp.model;

public enum Visibility {

    Private("private"),
    Public("public");


    private final String value;

    Visibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
