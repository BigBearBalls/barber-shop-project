package eu.senla.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "calendarServiceClient", url = "${feign.clients.calendar-service.url}")
public interface CalendarServiceClient {

    @GetMapping("{day}")
    Boolean checkDay(@PathVariable("day") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date);

}
