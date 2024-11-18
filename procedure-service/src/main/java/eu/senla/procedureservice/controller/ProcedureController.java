package eu.senla.procedureservice.controller;

import eu.senla.procedureservice.data.dto.request.CreateProcedureRequest;
import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;
import eu.senla.procedureservice.data.dto.response.ProceduresPageResponse;
import eu.senla.procedureservice.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/procedures/")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponseDTO save(@RequestBody CreateProcedureRequest request) {
        return procedureService.save(request);
    }

    @GetMapping("/{id}")
    public ProcedureDTO findProcedureByIdAndMasterId(@PathVariable Integer id, @RequestParam UUID masterId) {
        return procedureService.findProcedureByIdAndMasterId(id, masterId);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private void subscribeOnProcedure(@PathVariable Integer id, @RequestParam UUID masterId) {
        procedureService.subscribeOnProcedure(id, masterId);
    }

    @GetMapping
    public ProceduresPageResponse getPageOfProcedures(@PageableDefault Pageable pageable) {
        return procedureService.getPageOfProcedures(pageable);
    }
}
