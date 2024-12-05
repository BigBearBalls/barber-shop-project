package eu.senla.web.dto.otherDto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProcedureDto {

    private UUID id;
    private String procedureName;
    private BigDecimal procedurePrice;
    private Integer procedureDuration;
}
