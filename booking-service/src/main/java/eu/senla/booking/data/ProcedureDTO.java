package eu.senla.booking.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProcedureDTO {

    private UUID id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer duration;
}
