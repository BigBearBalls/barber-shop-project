package eu.senla.workingdayservice.controller.handler;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
