package eu.senla.procedureservice.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
    private UUID id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer duration;
}
