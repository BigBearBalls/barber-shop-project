package eu.senla.catalog.exception;

import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.UUID;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MasterNotFoundByIdException extends AbstractException{

    private final UUID id;

    public MasterNotFoundByIdException(UUID id, ExceptionInfo exceptionInfo) {
        super(exceptionInfo.getExceptionCode(), exceptionInfo.getExceptionMessage(), HttpStatus.NOT_FOUND);
        this.id = id;
    }
}
