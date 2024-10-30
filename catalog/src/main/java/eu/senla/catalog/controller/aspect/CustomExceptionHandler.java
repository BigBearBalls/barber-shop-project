package eu.senla.catalog.controller.aspect;

import eu.senla.catalog.exception.MasterNotFoundByIdException;
import eu.senla.catalog.exception.ProcedureExistException;
import eu.senla.catalog.exception.ProcedureNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = MasterNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseException> handleMasterNotFoundById(MasterNotFoundByIdException exception) {
        return new ResponseEntity<>(new ResponseException(messageSource.getMessage(exception.getExceptionMessage(),
                new Object[]{exception.getId()},
                LocaleContextHolder.getLocale()),
                exception.getExceptionCode()),
                exception.getHttpStatus());
    }

    @ExceptionHandler(value = ProcedureNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseException> handleProcedureNotFoundById(ProcedureNotFoundByIdException exception) {
        return new ResponseEntity<>(new ResponseException(messageSource.getMessage(exception.getExceptionMessage(),
                new Object[]{exception.getId()},
                LocaleContextHolder.getLocale()),
                exception.getExceptionCode()),
                exception.getHttpStatus());
    }

    @ExceptionHandler(value = ProcedureExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseException> handleProcedureExistException(ProcedureExistException exception) {
        return new ResponseEntity<>(new ResponseException(messageSource.getMessage(exception.getExceptionMessage(),
                new Object[]{exception.getId()},
                LocaleContextHolder.getLocale()),
                exception.getExceptionCode()),
                exception.getHttpStatus());
    }
}
