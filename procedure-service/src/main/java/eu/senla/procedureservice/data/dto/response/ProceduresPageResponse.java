package eu.senla.procedureservice.data.dto.response;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProceduresPageResponse {
    List<ProcedureDTO> procedures;
}
