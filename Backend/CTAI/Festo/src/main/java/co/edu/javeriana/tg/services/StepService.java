package co.edu.javeriana.tg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Operation;
import co.edu.javeriana.tg.entities.StepDefinition;
import co.edu.javeriana.tg.entities.WorkPlanDefinition;
import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.repositories.StepDefinitionRepository;

@Service
public class StepService {

    @Autowired
    private StepDefinitionRepository stepDefinitionRepository;

    @Autowired
    private OperationService operationService;

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

    public Long getWorkPlanOperationsCount(Long workPlanNumber) {
        return stepDefinitionRepository.countByWorkPlanWorkPlanNumber(workPlanNumber);
    }

    public StepDefinitionDTO saveStep(WorkPlanDefinition workPlan, Long stepNumber, String description,
            Long operationNumber, Long nextStepNumber,
            Long nextWhenError, Long newPartNumber, Long operationNumberType, Long resourceId, Long transportTime,
            String sqlToWrite, Long electricEnergy, Long compressedAir, Long workingTime, String freeString) {
        StepDefinitionDTO stepDefinitionDTO = null;
        try {
            StepDefinition stepDefinition = new StepDefinition();
            stepDefinition.setWorkPlan(workPlan);
            stepDefinition.setStepNumber(stepNumber);
            stepDefinition.setDescription(description);
            stepDefinition.setOperationNumber(operationService.get(operationNumber));
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
            stepDefinitionDTO = new StepDefinitionDTO(stepDefinition);
        } catch (Exception e) {
        }
        return stepDefinitionDTO;
    }
}
