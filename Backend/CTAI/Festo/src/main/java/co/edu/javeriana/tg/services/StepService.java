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

    public StepDefinitionDTO saveStep(Long workPlan, Long stepNumber, String description,
            Long operationNumber, Long nextStepNumber,
            Long nextWhenError, Long newPartNumber, Long operationNumberType, Long resourceId, Long transportTime,
            String sqlToWrite, Long electricEnergy, Long compressedAir, Long workingTime, String freeString) {
        StepDefinitionDTO stepDefinitionDTO = null;
        try {
            StepDefinition stepDefinition = new StepDefinition();
            stepDefinition.setWorkPlan(workPlan);
            stepDefinition.setStepNumber(stepNumber);
            stepDefinition.setDescription(description);
            stepDefinition.setOperationNumber(operationNumber);
            stepDefinition.setNextStepNumber(nextStepNumber);
            stepDefinition.setFirstStep(stepNumber == 1);
            stepDefinition.setNextWhenError(nextWhenError);
            stepDefinition.setNewPartNumber(newPartNumber);
            stepDefinition.setTransportTime(transportTime);
            stepDefinition.setSqlToWrite(sqlToWrite);
            stepDefinition.setCalculatedElectricEnergy(electricEnergy);
            stepDefinition.setCalculatedCompressedAir(compressedAir);
            stepDefinition.setCalculatedWorkingTime(workingTime);
            stepDefinition.setFreeText(freeString);
            stepDefinition.setResource(resourceId);
            stepDefinitionRepository.save(stepDefinition);
            stepDefinitionDTO = new StepDefinitionDTO(stepDefinition, workPlanService.getById(workPlan), operationService.get(operationNumber));
        } catch (Exception e) {
        }
        return stepDefinitionDTO;
    }
}
