package eu.senla.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.web.dto.authDto.AddWorkingDayRequest;
import eu.senla.web.dto.authDto.RegistrationRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WorkingDayController {

    private final WebClient webClient;

    @ModelAttribute("addWorkingDayRequest")
    public AddWorkingDayRequest addWorkingDayRequest() {
        return new AddWorkingDayRequest();
    }

    @GetMapping("/working-days")
    public String goToWorkingDays(Model model) {
        try {
            List<Map<String, Object>> unavailableDates = webClient.get()
                    .uri("/calendars/")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
                    })
                    .block();

            List<String> unavailableDateStrings = unavailableDates.stream()
                    .map(entry -> entry.get("date").toString())
                    .toList();
            model.addAttribute("unavailableDates", unavailableDateStrings);
            return "working-days";
        } catch (WebClientResponseException ex) {
//            Map<String, List<String>> fieldErrors = processErrorResponse(ex);
//            model.addAttribute("error", fieldErrors);
//            model.addAttribute("registrationRequest", registrationRequest);
            return "main";
        }
    }

    @PostMapping("/working-days")
    public String addWorkingDay(@ModelAttribute AddWorkingDayRequest addWorkingDayRequest, Model model, HttpSession session) throws JsonProcessingException {
        try {
            addWorkingDayRequest.setMasterId((String) session.getAttribute("accountId"));

            webClient.post()
                    .uri("/working-days/")
                    .bodyValue(addWorkingDayRequest)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            model.addAttribute("message", "Working day successfully added!");
            return "working-days";
        } catch (WebClientResponseException ex) {
            model.addAttribute("message", "This day is already a working day");
            return "working-days";
        }
    }

}
