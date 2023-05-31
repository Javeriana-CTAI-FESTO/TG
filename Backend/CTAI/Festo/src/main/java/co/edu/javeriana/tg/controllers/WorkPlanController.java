package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.WorkPlanService;

@RestController
@RequestMapping("/api/work-plan")
public class WorkPlanController {

    @Autowired
    private WorkPlanService workPlanService;

    @GetMapping
    public ResponseEntity<List<WorkPlanDTO>> getAll() {
        List<WorkPlanDTO> workPlans = workPlanService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }

}