package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.WorkPlanType;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.repositories.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.WorkPlanTypeRepository;

@Service
public class WorkPlanService {
    @Autowired
    private WorkPlanDefinitionRepository workPlanRepository;

    @Autowired
    private WorkPlanTypeRepository workPlanTypeRepository;

    public List<WorkPlanDTO> getAll(){
        return workPlanRepository.findAll().stream().map(WorkPlanDTO::new).collect(Collectors.toList());
    }

    public Map<Long, String> getTypes(){
       return workPlanTypeRepository.findAll().stream().collect(Collectors.toMap(WorkPlanType::getTypeNumber, WorkPlanType::getDescription));
    }

    public List<WorkPlanDTO> getOrdersByType(Long type){
        return workPlanRepository.findByWorkPlanTypeTypeNumber(type).stream().map(WorkPlanDTO::new).collect(Collectors.toList());
    }
}
