package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.repositories.WorkPlanDefinitionRepository;

@Service
public class WorkPlanService {
    @Autowired
    private WorkPlanDefinitionRepository workPlanRepository;

    public List<WorkPlanDTO> getAll(){
        return workPlanRepository.findAll().stream().map(WorkPlanDTO::new).collect(Collectors.toList());
    }
}
