package eu.senla.workingdayservice.controller;

import eu.senla.common.workingday.dto.request.RequestWorkingDayDTO;
import eu.senla.common.workingday.dto.response.ResponseWorkingDayDTO;
import eu.senla.workingdayservice.facade.WorkingDayFacade;
import eu.senla.workingdayservice.service.WorkingDayService;
import jakarta.validation.Valid;
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
    private final WorkingDayFacade workingDayFacade;

    @GetMapping("{id}")
    public ResponseWorkingDayDTO findById(@PathVariable UUID id) {
        return workingDayService.findById(id);
    }

    @GetMapping("master/{masterId}/date/{date}")
    public ResponseWorkingDayDTO findByMasterIdAndWorkingDate(@PathVariable UUID masterId,
                                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return workingDayService.findByMasterAndWorkingDate(masterId, date);
    }

    @PostMapping()
    public UUID addWorkingDay(@RequestBody @Valid RequestWorkingDayDTO requestWorkingDayDto) {
        return workingDayFacade.collectDataFromCalendar(requestWorkingDayDto);
    }
}
