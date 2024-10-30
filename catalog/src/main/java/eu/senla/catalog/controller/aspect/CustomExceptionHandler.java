package eu.senla.catalog.controller.aspect;

import eu.senla.catalog.exception.MasterNotFoundByIdException;
import eu.senla.catalog.exception.ProcedureExistException;
import eu.senla.catalog.exception.ProcedureNotFoundByIdException;
import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
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

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<ResponseException> responseExceptions = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(objectError ->
                responseExceptions.add(new ResponseException(messageSource
                        .getMessage(Objects.requireNonNull(objectError.getDefaultMessage()),
                                new Object[]{objectError.getField(), objectError.getRejectedValue()}, LocaleContextHolder.getLocale()),
                        ExceptionInfo.getExceptionCodeByMessage(objectError.getDefaultMessage()))));
        return new ResponseEntity<>(responseExceptions, HttpStatus.BAD_REQUEST);
    }

}
