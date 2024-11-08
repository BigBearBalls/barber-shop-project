package eu.senla.booking.service.exception;

public class TimeAlreadyBookedException extends ApplicationException {
    public TimeAlreadyBookedException(String message) {
        super(message);
    }

    public TimeAlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}
