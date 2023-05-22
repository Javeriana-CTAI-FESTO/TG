package co.edu.javeriana.tg.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.repositories.WorkPlanRepository;
import reactor.core.publisher.Flux;

@Service
public class WorkPlanService {
    @Autowired
    private WorkPlanRepository workPlanRepository;

    public Flux<WorkPlanDTO> getAllWorkPlans(){
        return Flux.fromIterable(workPlanRepository.findAll().stream().map(WorkPlanDTO::new).collect(Collectors.toList()));
    }
}
