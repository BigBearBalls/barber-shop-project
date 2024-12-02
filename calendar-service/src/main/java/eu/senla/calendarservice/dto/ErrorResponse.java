package eu.senla.calendarservice.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse implements Serializable {

    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private String path;

}
