package eu.senla.workingdayservice.controller.handler;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse implements Serializable {

    private String message;
    private String errorCode;
    private String path;
    private LocalDateTime timestamp;
}
