package eu.senla.booking.client;

import eu.senla.common.dto.ProcedureDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(url = "${feign.clients.procedure-service.url}", name = "procedureClient")
public interface ProcedureClient {

    @GetMapping("/{id}")
    ProcedureDTO findProcedureByIdAndMasterId(@PathVariable UUID id, @RequestParam UUID masterId);
}
