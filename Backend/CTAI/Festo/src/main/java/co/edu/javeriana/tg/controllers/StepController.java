package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.services.StepService;

@RestController
@RequestMapping("/api/step")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping
    public ResponseEntity<List<StepDefinitionDTO>> getAll() {
        List<StepDefinitionDTO> steps = stepService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (steps.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<StepDefinitionDTO>>(steps, status);
    }

}