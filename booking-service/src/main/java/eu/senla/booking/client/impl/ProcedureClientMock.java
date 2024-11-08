package eu.senla.booking.client.impl;

import eu.senla.booking.client.ProcedureClient;
import eu.senla.booking.data.ProcedureDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProcedureClientMock implements ProcedureClient {

    public ProcedureDTO findProcedureByIdAndMasterId(Integer id, UUID masterId) {
        return ProcedureDTO.builder()
                .id(id)
                .name("Procedure Mock")
                .duration(30)
                .price(BigDecimal.valueOf(10)).build();
    }
}
