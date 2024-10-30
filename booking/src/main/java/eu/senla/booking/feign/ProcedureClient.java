package eu.senla.booking.feign;

import eu.senla.booking.dto.ProcedureDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "procedureClient", url = "${feign.clients.procedure-client.url}")
public interface ProcedureClient {

    @GetMapping(value = "${feign.clients.procedure-client.get-procedure.url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProcedureDTO getProcedure(@RequestParam(name = "procedureId") Long procedureId);
}
