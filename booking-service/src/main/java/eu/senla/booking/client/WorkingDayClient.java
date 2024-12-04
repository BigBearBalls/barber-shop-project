package eu.senla.booking.client;

import eu.senla.common.booking.dto.response.ResponseWorkingDayDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.UUID;

@FeignClient(url = "${feign.clients.working-day-service.url}", name = "workingDayClient")
public interface WorkingDayClient {

    @GetMapping("master/{masterId}/date/{date}")
    ResponseWorkingDayDTO findByMasterIdAndWorkingDate(@PathVariable UUID masterId,
                                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

    @GetMapping("{id}")
    ResponseWorkingDayDTO findById(@PathVariable UUID id);

}
