package eu.senla.booking.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ERR_OBJECT_NOT_FOUND("The requested object not found! Message: "),
    ERR_UNKNOWN_CODE("Something went wrong!");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}