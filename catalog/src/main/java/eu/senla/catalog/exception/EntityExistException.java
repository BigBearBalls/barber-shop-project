package eu.senla.catalog.exception;

import eu.senla.catalog.util.enums.ExceptionInfo;
import org.springframework.http.HttpStatus;

public class EntityExistException extends AbstractException{

    public EntityExistException(String id, ExceptionInfo exceptionInfo) {
        super(id, exceptionInfo.getExceptionCode(), exceptionInfo.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }
}
