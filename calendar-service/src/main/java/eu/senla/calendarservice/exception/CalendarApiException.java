package eu.senla.calendarservice.exception;

import lombok.Getter;

@Getter
public abstract class CalendarApiException extends RuntimeException {

    private final String errorMessage;
    private final String errorCode;

    public CalendarApiException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
