package eu.senla.workingdayservice.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse implements Serializable {

    private LocalDateTime timestamp;
    private String code;
    private String message;
    private String path;

}
