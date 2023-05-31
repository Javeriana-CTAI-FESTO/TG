package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.ResourceForOperationService;

@RestController
@RequestMapping("/api/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @Autowired
    private ResourceForOperationService resourceForOperationService;

    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAll() {
        List<OperationDTO> workPlans = operationService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OperationDTO>>(workPlans, status);
    }

    @GetMapping("/{operationId}/resources")
    public ResponseEntity<ResourceForOperationDTO> getResources(@PathVariable Long operationId) {
        ResourceForOperationDTO workPlans = resourceForOperationService.getResourcesGivenOperation(operationId);
        HttpStatus status = HttpStatus.OK;
        if (workPlans == null)
            status = HttpStatus.IM_USED;
        else if (workPlans.getResources().isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<ResourceForOperationDTO>(workPlans, status);
    }
}
