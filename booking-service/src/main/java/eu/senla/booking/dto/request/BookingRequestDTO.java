package eu.senla.booking.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
public record BookingRequestDTO(Integer id, Integer procedureId, UUID masterId,
    UUID clientId, LocalTime reservationStart, LocalDate workingDate){
}
