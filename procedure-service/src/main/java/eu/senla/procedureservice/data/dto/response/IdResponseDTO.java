package eu.senla.procedureservice.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class IdResponseDTO {
    private UUID id;
}

