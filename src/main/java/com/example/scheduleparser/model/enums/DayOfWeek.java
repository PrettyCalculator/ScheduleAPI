package com.example.scheduleparser.model.enums;

import lombok.Getter;

@Getter
public enum DayOfWeek {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);
    private final int code;

    DayOfWeek(int code) {
        this.code = code;
    }

    public static DayOfWeek fromCode(int code) {
        for (DayOfWeek day : values()) {
            if (day.code == code) {
                return day;
            }
        }
        throw new IllegalArgumentException("Unknown day code: " + code);
    }
}
