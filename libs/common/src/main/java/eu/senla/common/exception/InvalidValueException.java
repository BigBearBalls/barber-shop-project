package eu.senla.common.exception;

import eu.senla.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidValueException extends ApiException {

  public InvalidValueException(String message, ErrorCode errorCode) {
    super(message, HttpStatus.BAD_REQUEST, errorCode);
  }

  public InvalidValueException(ErrorCode errorCode) {
    this(errorCode.getMessage(), errorCode);
  }
}
