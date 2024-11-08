package eu.senla.booking.service.exception;

public class ValidationException extends ApplicationException{

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
