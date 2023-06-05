package co.edu.javeriana.tg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Operation;
import co.edu.javeriana.tg.entities.StepDefinition;
import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.repositories.StepDefinitionRepository;

@Service
public class StepService {

    @Autowired
    private StepDefinitionRepository stepDefinitionRepository;

    public List<StepDefinitionDTO> getAll() {
        return stepDefinitionRepository.findAll().stream().map(StepDefinitionDTO::new).collect(Collectors.toList());
    }

    public WorkPlanTimeAux getWorkPlanTime(Long workPlanNumber) {
        List<Operation> operations = new ArrayList<>();
        List<StepDefinition> steps = stepDefinitionRepository.findByWorkPlanWorkPlanNumber(workPlanNumber);
        for (StepDefinition step : steps) {
            operations.add(step.getOperation());
        }
        return new WorkPlanTimeAux(steps.stream().mapToLong(StepDefinition::getTransportTime).sum(), operations);
    }

}
