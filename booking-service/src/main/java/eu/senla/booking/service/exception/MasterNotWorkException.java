package eu.senla.booking.service.exception;

public class MasterNotWorkException extends ApplicationException{
    public MasterNotWorkException(String message) {
        super(message);
    }

    public MasterNotWorkException(String message, Throwable cause) {
        super(message, cause);
    }
}
