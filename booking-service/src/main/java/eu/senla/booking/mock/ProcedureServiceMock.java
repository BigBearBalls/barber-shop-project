package eu.senla.booking.mock;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProcedureServiceMock {

    public ProcedureMock findProcedureByIdAndMasterId(Integer id, UUID masterId) {
        return ProcedureMock.builder()
                .id(id)
                .name("Procedure Mock")
                .duration(30)
                .price(BigDecimal.valueOf(10)).build();
    }
}
