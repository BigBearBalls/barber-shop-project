package eu.senla.procedureservice.data.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProcedureDTO {
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer procedureDuration;
}
