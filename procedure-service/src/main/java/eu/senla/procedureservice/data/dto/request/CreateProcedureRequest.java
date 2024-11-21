package eu.senla.procedureservice.data.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProcedureRequest {
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer procedureDuration;
}
