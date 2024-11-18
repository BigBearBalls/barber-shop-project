package eu.senla.calendarservice.controller;

import eu.senla.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/calendar/")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("days-off/{day}")
    public LocalDate setDayOff(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate day) {
        return calendarService.setDayOff(day);
    }

    @DeleteMapping("working-day/{day}")
    public void cancelDayOff(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate day) {
        calendarService.setWorkingDay(day);
    }

    @GetMapping("{day}")
    public Boolean checkDay(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate day) {
        return calendarService.checkDay(day);
    }
}
