package eu.senla.workingdayservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/working-days")
@RequiredArgsConstructor
public class WorkingDayController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getHello() {
        return "OK";
    }
}
