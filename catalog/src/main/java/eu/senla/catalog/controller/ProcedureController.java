package eu.senla.catalog.controller;

import eu.senla.catalog.dto.ProcedureDto;
import eu.senla.catalog.service.MasterService;
import eu.senla.catalog.service.ProcedureService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/procedures")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;
    private final MasterService masterService;

    @GetMapping
    public List<ProcedureDto> getAllProcedures() {
        return procedureService.getProcedures();
    }

    @GetMapping("/{masterId}")
    public List<ProcedureDto> getProceduresByMasterId(@PathVariable UUID masterId) {
        return masterService.getProcedures(masterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedureDto addProcedure(@Valid @RequestBody ProcedureDto procedureDto) {
         return procedureService.addProcedure(procedureDto);
    }

    @DeleteMapping("/{procedureId}")
    public void deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
    }

    @GetMapping("/{procedureId}")
    public ProcedureDto getProcedure(@PathVariable Long id) {
        return procedureService.getProcedure(id);
    }

    @PutMapping("/{procedureId}")
    public ProcedureDto updateProcedure(@Valid @RequestBody ProcedureDto procedureDto,
                                        @PathVariable Long catalogElementId) {
        return procedureService.updateProcedure(catalogElementId, procedureDto);
    }
}