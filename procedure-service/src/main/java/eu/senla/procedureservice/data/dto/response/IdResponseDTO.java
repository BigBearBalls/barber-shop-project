package eu.senla.procedureservice.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class IdResponseDTO {
    private UUID id;
}

