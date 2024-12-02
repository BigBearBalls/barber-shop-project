package eu.senla.procedureservice.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
