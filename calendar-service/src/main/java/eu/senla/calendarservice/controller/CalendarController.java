package eu.senla.calendarservice.controller;

import eu.senla.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/calendars/")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("days-off/{day}")
    public LocalDate setDayOff(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate day) {
        return calendarService.setDayOff(day);
    }

    @DeleteMapping("working-days/{day}")
    public void cancelDayOff(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate day) {
        calendarService.cancelDayOff(day);
    }

    @GetMapping("{day}")
    public Boolean checkDay(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate day) {
        return calendarService.checkDay(day);
    }
}
