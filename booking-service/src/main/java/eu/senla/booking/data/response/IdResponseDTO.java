package eu.senla.booking.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class IdResponseDTO {
    private UUID id;
}

