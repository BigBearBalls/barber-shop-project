package eu.senla.booking.controller;

import eu.senla.booking.dto.DateTimeRequest;
import eu.senla.booking.dto.DayTimesResponse;
import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.service.DayTimeService;
import eu.senla.booking.service.MasterTimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master")
@Validated
public class MasterController {

    private final MasterTimetableService masterTimeTableService;

    private final DayTimeService dayTimeService;

    @GetMapping("/{masterId}")
    public GetMasterFreeTimesResponse getMasterFreeTime(@PathVariable UUID masterId) {
        return masterTimeTableService.getMasterFreeTimes(masterId);
    }

    @PostMapping("/{masterId}/timetable")
    @ResponseStatus(HttpStatus.CREATED)
    private void markDayAsWorkDay(@PathVariable UUID masterId, @RequestBody DateTimeRequest request) {
        masterTimeTableService.markDayAsWorkDay(masterId, request);
    }

    @GetMapping("/time")
    private DayTimesResponse getDayTimes() {
        return dayTimeService.getDayTimes();
    }
}
