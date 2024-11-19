package eu.senla.booking.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProcedureDTO {

    private UUID id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer duration;
}
