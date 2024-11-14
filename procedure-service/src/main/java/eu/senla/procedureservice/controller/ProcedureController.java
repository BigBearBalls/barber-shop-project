package eu.senla.procedureservice.controller;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;
import eu.senla.procedureservice.service.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/procedures")
@AllArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;

    @PostMapping
    public ResponseEntity<IdResponseDTO> save(@RequestBody ProcedureDTO procedureDTO) {
        return ResponseEntity
                .ok(procedureService.save(procedureDTO));

    };
}
