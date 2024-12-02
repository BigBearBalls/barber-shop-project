package eu.senla.workingdayservice.controller;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;
import eu.senla.workingdayservice.service.WorkingDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/working-days/")
@RequiredArgsConstructor
public class WorkingDayController {

    private final WorkingDayService workingDayService;

    @GetMapping("{id}")
    public ResponseWorkingDayDto findById(@PathVariable UUID id) {
        return workingDayService.findById(id);
    }

    @GetMapping("master/{masterId}/date/{date}")
    public ResponseWorkingDayDto findByMasterIdAndWorkingDate(@PathVariable UUID masterId,
                                                              @PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return workingDayService.findByMasterAndWorkingDate(masterId, date);
    }

    @PostMapping()
    public UUID addWorkingDay(@RequestBody RequestWorkingDayDto requestWorkingDayDto) {
        return workingDayService.save(requestWorkingDayDto);
    }
}
