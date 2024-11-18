package eu.senla.workingdayservice.exception;

public class NotFoundByDateAndByIdException extends AbstractException {

    public NotFoundByDateAndByIdException(String code, String message) {
        super(code, message);
    }
}
