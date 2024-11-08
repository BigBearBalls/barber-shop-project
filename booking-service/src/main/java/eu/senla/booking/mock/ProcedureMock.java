package eu.senla.booking.mock;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProcedureMock {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer duration;
}
