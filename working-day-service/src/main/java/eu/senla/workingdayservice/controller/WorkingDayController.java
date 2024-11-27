package eu.senla.workingdayservice.controller;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;
import eu.senla.workingdayservice.facade.WorkingDayFacade;
import eu.senla.workingdayservice.service.WorkingDayService;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/working-days/")
@RequiredArgsConstructor
public class WorkingDayController {

    private final WorkingDayService workingDayService;
    private final WorkingDayFacade workingDayFacade;

    @GetMapping("{id}")
    public ResponseWorkingDayDto findById(@PathVariable UUID id) {
        return workingDayService.findById(id);
    }

    @GetMapping("master/{masterId}/date/{date}")
    public ResponseWorkingDayDto findByMasterIdAndWorkingDate(@PathVariable UUID masterId,
                                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return workingDayService.findByMasterAndWorkingDate(masterId, date);
    }

    @PostMapping()
    public UUID addWorkingDay(@RequestBody @Valid RequestWorkingDayDto requestWorkingDayDto) {
        return workingDayFacade.collectDataFromCalendar(requestWorkingDayDto);
    }
}
