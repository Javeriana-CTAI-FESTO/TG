package co.edu.javeriana.tg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.WorkPlanService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/work-plan")
public class WorkPlanController {

    @Autowired
    private WorkPlanService workPlanService;

    @GetMapping
    public Mono<ServerResponse> getAll() {
        Flux<WorkPlanDTO> workPlans=workPlanService.getAllWorkPlans();
        Mono<ServerResponse> response = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(workPlans, WorkPlanDTO.class);
        return response;
    }

}
