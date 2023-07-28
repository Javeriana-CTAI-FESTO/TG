package co.edu.javeriana.tg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;

@Service
public class StepService {

    private final StepDefinitionRepository stepDefinitionRepository;

    private final WorkPlanService workPlanService;

    private final OperationService operationService;

    public StepService(StepDefinitionRepository stepDefinitionRepository, WorkPlanService workPlanService, OperationService operationService) {
        this.stepDefinitionRepository = stepDefinitionRepository;
        this.workPlanService = workPlanService;
        this.operationService = operationService;
    }

    public List<StepDefinitionDTO> getAll() {
        return stepDefinitionRepository.findAll().stream().map(stepDefinition -> new StepDefinitionDTO(stepDefinition, workPlanService.getById(stepDefinition.getWorkPlan()), operationService.get(stepDefinition.getOperation()))).collect(Collectors.toList());
    }

    public WorkPlanTimeAux getWorkPlanTime(Long workPlanNumber) {
        List<Long> operations = new ArrayList<>();
        List<StepDefinition> steps = stepDefinitionRepository.findByWorkPlan(workPlanNumber);
        steps.forEach(step -> operations.add(step.getOperation()));
        return new WorkPlanTimeAux(steps.stream().mapToLong(StepDefinition::getTransportTime).sum(), operations);
    }

    public Long getWorkPlanOperationsCount(Long workPlanNumber) {
        return stepDefinitionRepository.countByWorkPlan(workPlanNumber);
    }
}
