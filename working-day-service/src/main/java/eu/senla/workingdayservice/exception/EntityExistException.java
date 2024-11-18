package eu.senla.workingdayservice.exception;

public class EntityExistException extends AbstractException {

    public EntityExistException(String code, String message) {
        super(code, message);
    }
}
