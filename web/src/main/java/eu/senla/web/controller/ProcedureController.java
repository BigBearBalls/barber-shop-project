package eu.senla.web.controller;

import eu.senla.web.dto.otherDto.ProcedureDto;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProcedureController {

    private final WebClient webClient;

    @GetMapping(value="/assign-master-procedure/{id}")
    public String assignMasterProcedure(@PathVariable("id") UUID id, HttpSession session) {

        try {

            webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/procedures/{id}")
                            .queryParam("masterId", UUID.fromString((String) session.getAttribute("accountId")))
                            .build(id))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

        } catch (Exception e) {
            return "modal/assign-master-procedure-error :: assign-master-procedure";
        }

        return "modal/assign-master-procedure :: assign-master-procedure";
    }

    @PostMapping(value="/add-procedure")
    public ModelAndView addProcedure(@ModelAttribute ProcedureDto procedureDto) {
        ModelAndView modelAndView = new ModelAndView();

        try {

            webClient.post().uri("/procedures/")
                    .bodyValue(procedureDto)
                    .retrieve()
                    .bodyToMono(ProcedureDto.class)
                    .block();

        } catch (Exception e) {
            modelAndView.setViewName("modal/add-procedure-error :: add-procedure");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/services");
        return modelAndView;
    }

    @GetMapping(value="/add-procedure")
    public String addProcedure(ModelMap model) {
        ProcedureDto procedure = new ProcedureDto();
        model.addAttribute("procedure", procedure);
        return "modal/add-procedure :: add-procedure";
    }

    @GetMapping("/services")
    public String getAllProcedures(Model model) {
        try {

            Mono<List> response = webClient
                    .get()
                    .uri("/procedures/")
                    .retrieve()
                    .bodyToMono(List.class);
            List<?> procedures = response.block();
            model.addAttribute("procedures", procedures);

        } catch (Exception e) {
            model.addAttribute("error", "Ошибка получения данных с бэкенда: " + e.getMessage());
        }

        return "services";
    }

}
