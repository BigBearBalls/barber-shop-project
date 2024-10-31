package eu.senla.catalog.exception;

import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class NotFoundByIdException extends AbstractException{

    public NotFoundByIdException(String id, ExceptionInfo exceptionInfo) {
        super(id, exceptionInfo.getExceptionCode(), exceptionInfo.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }
}
