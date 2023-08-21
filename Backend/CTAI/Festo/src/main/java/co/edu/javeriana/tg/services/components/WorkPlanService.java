package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
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
            WorkPlanTypeRepository workPlanTypeRepository, OperationService operationService,
            StepDefinitionRepository stepDefinitionRepository) {
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
            if (!workPlanRepository.existsById(createRequest.getWorkPlanNumber())){
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
                this.saveStep(workplanEntity.getWorkPlanNumber(), Long.valueOf((i + 1) * 10),
                        (i < createRequest.getOperations().length - 1) ? Long.valueOf((i + 2) * 10) : 0,
                        createRequest.getOperations()[i].getNextWhenError(),
                        createRequest.getOperations()[i].getNewPartNumber(),
                        createRequest.getOperations()[i].getTransportTime(),
                        createRequest.getOperations()[i].getOperationNumber(),
                        createRequest.getOperations()[i].getElectricEnergy(),
                        createRequest.getOperations()[i].getCompressedAir(),
                        createRequest.getOperations()[i].getResource(),
                        createRequest.getOperations()[i].getWorkingTime());
            }
        }
        } catch (Exception e) {
        }
        return workPlan;
    }

    public StepDefinitionDTO saveStep(Long workPlan, Long stepNumber, Long nextStepNumber, Long nextWhenError,
            Long newPartNumber, Long transportTime, Long operationNumber, Long electricEnergy, Long compressedAir,
            Long resourceId, Long workingTime) {
        StepDefinitionDTO stepDefinitionDTO = null;
        try {
            OperationDTO operationDTO = operationService.get(operationNumber);
            StepDefinition stepDefinition = new StepDefinition();
            stepDefinition.setWorkPlan(workPlan);
            stepDefinition.setStepNumber(stepNumber);
            stepDefinition.setDescription(operationDTO.getDescription());
            stepDefinition.setOperationNumber(operationNumber);
            stepDefinition.setNextStepNumber(nextStepNumber);
            stepDefinition.setFirstStep(stepNumber == 1);
            stepDefinition.setNextWhenError(nextWhenError);
            stepDefinition.setNewPartNumber(newPartNumber);
            stepDefinition.setTransportTime(transportTime);
            stepDefinition.setSqlToWrite(operationDTO.getQueryToWrite());
            stepDefinition.setCalculatedElectricEnergy(electricEnergy);
            stepDefinition.setCalculatedCompressedAir(compressedAir);
            stepDefinition.setCalculatedWorkingTime(workingTime);
            stepDefinition.setFreeText(operationDTO.getFreeText());
            stepDefinition.setResource(resourceId);
            stepDefinitionRepository.save(stepDefinition);
            stepDefinitionDTO = new StepDefinitionDTO(stepDefinition, this.getWorkplanById(workPlan),
                    operationService.get(operationNumber));
            // Set Parameters

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

    public WorkPlanDTO getWorkplanById(Long id) {
        WorkPlanDTO workPlan = null;
        try {
            workPlan = new WorkPlanDTO(workPlanRepository.findById(id).get(), workPlanTypeRepository
                    .findByTypeNumber(workPlanRepository.findById(id).get().getWorkPlanType()).getDescription());
        } catch (Exception e) {
        }
        return workPlan;
    }

    public WorkPlanWithStepsDTO getWithStepsById(Long id) {
        return new WorkPlanWithStepsDTO(this.getWorkplanById(id),
                stepDefinitionRepository.findByWorkPlan(id).stream()
                        .map(step -> new StepDefinitionDTO(step, operationService.get(step.getOperation())))
                        .collect(Collectors.toList()));
    }

    public Long getWorkPlanTypeByWorkPlanNumber(Long workPlanNumber) {
        try {
            return workPlanRepository.findById(workPlanNumber).get().getWorkPlanType();
        } catch (Exception e) {

        }
        return 0l;
    }
}