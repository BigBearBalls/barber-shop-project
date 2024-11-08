package eu.senla.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProcedureDTO {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer duration;
}
