package eu.senla.catalog.exception;

import eu.senla.catalog.util.enums.ExceptionInfo;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProcedureExistException extends AbstractException{

    private final long id;

    public ProcedureExistException(long id, ExceptionInfo exceptionInfo) {
        super(exceptionInfo.getExceptionCode(), exceptionInfo.getExceptionMessage(), HttpStatus.CONFLICT);
        this.id = id;
    }
}
