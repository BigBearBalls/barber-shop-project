package eu.senla.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.web.dto.authDto.AccountRequest;
import eu.senla.web.dto.authDto.LoginRequest;
import eu.senla.web.dto.authDto.LoginResponse;
import eu.senla.web.dto.authDto.RegistrationRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final WebClient webClient;
    private final SpringResourceTemplateResolver springResourceTemplateResolver;

    @ModelAttribute("registrationRequest")
    public RegistrationRequest registrationRequest() {
        return new RegistrationRequest();
    }

    @ModelAttribute("loginRequest")
    public LoginRequest loginRequest() {
        return new LoginRequest();
    }

    @GetMapping("/")
    public String welcome(Model model) {
        return "welcome";
    }


    @PostMapping("/do_reg")
    public String doRegistration(@ModelAttribute RegistrationRequest registrationRequest, Model model) {
        try {
            webClient.post()
                    .uri("/auth/registration")
                    .bodyValue(registrationRequest)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            model.addAttribute("info", "successful registration");
            return "welcome";
        } catch (WebClientResponseException ex) {
            Map<String, List<String>> fieldErrors = processErrorResponse(ex);
            model.addAttribute("error", fieldErrors);
            model.addAttribute("registrationRequest", registrationRequest);
            return "welcome";
        }
    }

    @PostMapping("/do_auth")
    public String doAuth(@ModelAttribute LoginRequest loginRequest, Model model, HttpSession session) {
        try {
            LoginResponse response = webClient.post()
                    .uri("/auth/login")
                    .bodyValue(loginRequest)
                    .retrieve()
                    .bodyToMono(LoginResponse.class)
                    .block();

            AccountRequest accountRequest = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/account/")
                            .queryParam("email", loginRequest.getEmail())
                            .build())
                    .retrieve()
                    .bodyToMono(AccountRequest.class)
                    .block();

            session.setAttribute("accountId", accountRequest.getId());

//            session.setAttribute("accessToken", response.getAccessToken());
//            session.setAttribute("refreshToken", response.getRefreshToken());

            return "main";
        } catch (WebClientResponseException ex) {
            Map<String, List<String>> fieldErrors = processErrorResponse(ex);
            model.addAttribute("error", fieldErrors);
            return "welcome";
        }

    }

    private Map<String, List<String>> processErrorResponse(WebClientResponseException ex) {
        Map<String, List<String>> fieldErrors = new HashMap<>();
        try {
            Map<String, Object> errorResponse = new ObjectMapper().readValue(ex.getResponseBodyAsString(), Map.class);
            String errorCode = (String) errorResponse.get("code");

            if ("ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION".equals(errorCode)) {
                if (errorResponse.containsKey("violations") && errorResponse.get("violations") instanceof List) {
                    List<Map<String, String>> violations = (List<Map<String, String>>) errorResponse.get("violations");
                    for (Map<String, String> violation : violations) {
                        String fieldName = violation.get("fieldName");
                        String message = violation.get("message");
                        fieldErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(message);
                    }
                }
            } else if ("ERR_USER_ALREADY_EXISTS".equals(errorCode)) {
                fieldErrors.computeIfAbsent("general", k -> new ArrayList<>())
                        .add((String) errorResponse.get("message"));
            } else {
                fieldErrors.computeIfAbsent("general", k -> new ArrayList<>())
                        .add((String) errorResponse.getOrDefault("message", "An unexpected error occurred. Please try again."));
            }
        } catch (JsonProcessingException e) {
            fieldErrors.computeIfAbsent("general", k -> new ArrayList<>())
                    .add("Failed to parse error response. Please try again later.");
            System.err.println("Failed to parse error response: " + e.getMessage());
        }
        return fieldErrors;
    }

    @GetMapping("/bookings")
    public String goToBookings(Model model) {
        return "bookings";
    }

}