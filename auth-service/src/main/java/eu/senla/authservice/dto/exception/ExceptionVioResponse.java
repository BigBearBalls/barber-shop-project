package eu.senla.authservice.dto.exception;

import eu.senla.authservice.enums.ErrorCode;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExceptionVioResponse {

    private LocalDateTime timestamp;
    private ErrorCode code;
    private String message;
    private String path;
    private List<Violation> violations; // Новый список ошибок

}
