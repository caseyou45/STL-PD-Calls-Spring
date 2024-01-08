package com.stlpd.enums;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SortDirection fromString(String input) {
        for (SortDirection direction : SortDirection.values()) {
            if (direction.name().equalsIgnoreCase(input) || direction.value.equalsIgnoreCase(input)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid Sort Direction: " + input);
    }

}
