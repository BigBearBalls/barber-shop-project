package eu.senla.procedureservice.data.dto.response;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProceduresPageResponse {

    List<ProcedureDTO> procedures;

    long totalCount;
}
