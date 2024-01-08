package com.stlpd.enums;

public enum SortMethod {
    DATETIME("datetimeSort"),
    TYPE("typeSort"),
    LOCATION("locationSort");

    private final String value;

    SortMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SortMethod fromString(String input) {
        for (SortMethod Method : SortMethod.values()) {
            if (Method.name().equalsIgnoreCase(input) || Method.value.equalsIgnoreCase(input)) {
                return Method;
            }
        }
        throw new IllegalArgumentException("Invalid Sort Method: " + input);
    }

}
