package eu.senla.calendarservice.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse implements Serializable {

    private HttpStatus status;
    private String message;
    private String errorCode;
    private String path;
    private LocalDateTime timestamp;

}
