package eu.senla.calendarservice.exception;

public class InvalidDateException extends CalendarApiException {
    public InvalidDateException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }
}
