package com.project.abc_ignite.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CLASS_NOT_FOUND("CLASS_NOT_FOUND", "Class not found"),
    INVALID_DATE("INVALID_DATE", "Invalid date provided"),
    CAPACITY_EXCEEDED("CAPACITY_EXCEEDED", "Class capacity exceeded"),
    INVALID_CAPACITY("INVALID_CAPACITY", "Capacity must be at least 1"),
    INVALID_END_DATE("INVALID_END_DATE", "End date must be in the future"),
    END_DATE_BEFORE_START_DATE("END_DATE_BEFORE_START_DATE", "End date must be after start date");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
