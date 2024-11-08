package eu.senla.booking.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private String message;
    private int code;

}