package eu.senla.procedureservice.data.dto.exception;

import eu.senla.procedureservice.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private ErrorCode errorCode;
    private String message;
    private String path;

}
