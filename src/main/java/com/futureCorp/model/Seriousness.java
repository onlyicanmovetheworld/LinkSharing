package com.futureCorp.model;

public enum Seriousness {
    Serious("serious"),
    VerySerious("very serious"),
    Casual("casual");

    private final String value;

    Seriousness(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
