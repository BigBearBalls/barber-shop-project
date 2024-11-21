package eu.senla.procedureservice.data.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
    private UUID id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer duration;
}
