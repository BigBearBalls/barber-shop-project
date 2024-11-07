package eu.senla.booking.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class BookingResponseDTO {
    private String clientFirstName;
    private String clientLastName;
    private String masterFirstname;
    private String masterLastName;
    private LocalDate date;
    private LocalTime time;
    private String procedure;
    private int duration;
    private double price;
}
