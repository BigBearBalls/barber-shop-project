package eu.senla.procedureservice.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
    private String id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer procedureDuration;
}
