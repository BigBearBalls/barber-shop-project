package eu.senla.booking.client;

import eu.senla.booking.configuration.FeignConfig;
import eu.senla.booking.data.ResponseWorkingDayDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.UUID;

@FeignClient(url = "${feign.clients.working-day-service.url}", name = "workingDayClient", configuration = FeignConfig.class)
public interface WorkingDayClient {

    @GetMapping("{masterId}/master/{date}/date")
    ResponseWorkingDayDto findByMasterIdAndWorkingDate(@PathVariable UUID masterId,
                                                       @PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date);

    @GetMapping("{id}")
    ResponseWorkingDayDto findById(@PathVariable UUID id);

}
