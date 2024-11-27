package eu.senla.workingdayservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(url = "${feign.clients.calendar-service.url}", name = "calendarClient")
public interface CalendarClient {

    @GetMapping("{day}")
    Boolean checkDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day);

}
