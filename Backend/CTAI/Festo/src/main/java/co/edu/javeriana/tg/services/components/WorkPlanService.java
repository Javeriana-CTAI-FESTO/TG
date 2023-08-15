package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import co.edu.javeriana.tg.entities.managed.WorkPlanType;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;

@Component
public class WorkPlanService {

    private final WorkPlanDefinitionRepository workPlanRepository;

    private final WorkPlanTypeRepository workPlanTypeRepository;

    private final OperationService operationService;

    private final StepDefinitionRepository stepDefinitionRepository;

    public WorkPlanService(WorkPlanDefinitionRepository workPlanRepository,
            WorkPlanTypeRepository workPlanTypeRepository, OperationService operationService, StepDefinitionRepository stepDefinitionRepository) {
        this.workPlanRepository = workPlanRepository;
        this.workPlanTypeRepository = workPlanTypeRepository;
        this.operationService = operationService;
        this.stepDefinitionRepository = stepDefinitionRepository;
    }

    public List<WorkPlanDTO> getAll() {
        return workPlanRepository.findAll().stream()
                .map(workPlan -> new WorkPlanDTO(workPlan,
                        workPlanTypeRepository.findByTypeNumber(workPlan.getWorkPlanType()).getDescription()))
                .collect(Collectors.toList());
    }

    public Map<Long, String> getTypes() {
        return workPlanTypeRepository.findAll().stream()
                .collect(Collectors.toMap(WorkPlanType::getTypeNumber, WorkPlanType::getDescription));
    }

    public WorkPlanDTO createWorkPlan(CreateWorkPlanAux createRequest) {
        WorkPlanDTO workPlan = null;
        try {
            WorkPlanDefinition workplanEntity = new WorkPlanDefinition();
            workplanEntity.setWorkPlanNumber(createRequest.getWorkPlanNumber());
            workplanEntity.setWorkPlanType(createRequest.getWorkPlanType());
            workplanEntity.setDescription(createRequest.getDescription());
            workplanEntity.setShortDescription(createRequest.getShortDescription());
            workplanEntity.setPictureNumber(createRequest.getPictureNumber());
            workplanEntity.setPartNumber(createRequest.getPartNumber());
            workPlanRepository.save(workplanEntity);
            workPlan = new WorkPlanDTO(workplanEntity,
                    workPlanTypeRepository.findByTypeNumber(createRequest.getWorkPlanType()).getDescription());
            for (int i = 0; i < createRequest.getOperations().length; i++) {
                this.saveStep(workplanEntity.getWorkPlanNumber(), Long.valueOf(i+1),
                        createRequest.getOperations()[i].getDescription(),
                        createRequest.getOperations()[i].getOperationNumber(),
                        createRequest.getOperations()[i].getNextStepNumber(),
                        createRequest.getOperations()[i].getErrorStepNumber(),
                        createRequest.getOperations()[i].getNewPartNumber(),
                        createRequest.getOperations()[i].getOperationNumberType(),
                        createRequest.getOperations()[i].getResourceId(),
                        createRequest.getOperations()[i].getTransportTime(),
                        createRequest.getOperations()[i].getSqlToWrite(),
                        createRequest.getOperations()[i].getElectricEnergyCalc(),
                        createRequest.getOperations()[i].getCompressedAirCalc(),
                        createRequest.getOperations()[i].getWorkingTimeCalc(),
                        createRequest.getOperations()[i].getFreeText());
            }
        } catch (Exception e) {
        }
        return workPlan;
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
            stepDefinitionDTO = new StepDefinitionDTO(stepDefinition, this.getById(workPlan), operationService.get(operationNumber));
        } catch (Exception e) {
        }
        return stepDefinitionDTO;
    }

    public List<WorkPlanDTO> getWorkPlansByType(Long type) {
        return workPlanRepository.findByWorkPlanType(type).stream()
                .map(workPlan -> new WorkPlanDTO(workPlan,
                        workPlanTypeRepository.findByTypeNumber(workPlan.getWorkPlanType()).getDescription()))
                .collect(Collectors.toList());
    }

    public WorkPlanDTO getById(Long id) {
        WorkPlanDTO workPlan = null;
        try {
            workPlan = new WorkPlanDTO(workPlanRepository.findById(id).get(), workPlanTypeRepository
                    .findByTypeNumber(workPlanRepository.findById(id).get().getWorkPlanType()).getDescription());
        } catch (Exception e) {
        }
        return workPlan;
    }
}
