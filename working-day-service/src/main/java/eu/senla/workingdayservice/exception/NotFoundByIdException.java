package eu.senla.workingdayservice.exception;

public class NotFoundByIdException extends AbstractException {

    public NotFoundByIdException(String code, String message) {
        super(code, message);
    }
}
