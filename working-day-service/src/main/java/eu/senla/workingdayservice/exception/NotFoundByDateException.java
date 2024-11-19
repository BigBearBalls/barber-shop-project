package eu.senla.workingdayservice.exception;

public class NotFoundByDateException extends AbstractException {

    public NotFoundByDateException(String code, String message) {
        super(code, message);
    }
}
