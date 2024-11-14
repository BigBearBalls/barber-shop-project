package eu.senla.calendarservice.exception;

public class EmptyDateException extends CalendarApiException {

    public EmptyDateException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }
}
