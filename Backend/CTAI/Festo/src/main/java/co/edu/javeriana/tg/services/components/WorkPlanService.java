package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.StepToPerformInWorkplanAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;
import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import co.edu.javeriana.tg.entities.managed.WorkPlanType;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;

@Component
@Transactional
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
            for (int i = 0; i < createRequest.getSteps().length; i++) {
                StepToPerformInWorkplanAux stepToPerformInWorkplan = createRequest.getSteps()[i];
                StepDefinitionPK stepToCopyPK = new StepDefinitionPK(stepToPerformInWorkplan.getDefinedStepWorkPlanNumber(), stepToPerformInWorkplan.getDefinedStepNumber());
                StepDefinition stepToCopy = stepDefinitionRepository.findById(stepToCopyPK).get();

                StepDefinition newStepDefinition = new StepDefinition();
                newStepDefinition.setWorkPlan(stepToCopy.getWorkPlan());
                newStepDefinition.setStepNumber(stepToPerformInWorkplan.getThisStepNumber());
                newStepDefinition.setDescription(stepToCopy.getDescription());
                newStepDefinition.setOperationNumber(stepToCopy.getOperation());
                newStepDefinition.setNextStepNumber(stepToPerformInWorkplan.getNextStepNumber());
                newStepDefinition.setFirstStep(stepToPerformInWorkplan.getFirstStep());
                newStepDefinition.setNextWhenError(stepToPerformInWorkplan.getErrorStepNumber());
                newStepDefinition.setNewPartNumber(stepToCopy.getNewPartNumber());
                newStepDefinition.setOperationNumberType(stepToCopy.getOperationNumberType());
                newStepDefinition.setResource(stepToCopy.getResource());
                newStepDefinition.setTransportTime(stepToCopy.getTransportTime());
                newStepDefinition.setError(stepToPerformInWorkplan.getErrorStep());
                newStepDefinition.setSqlToWrite(stepToCopy.getSqlToWrite());
                newStepDefinition.setCalculatedElectricEnergy(stepToCopy.getCalculatedElectricEnergy());
                newStepDefinition.setCalculatedCompressedAir(stepToCopy.getCalculatedCompressedAir());
                newStepDefinition.setCalculatedWorkingTime(stepToCopy.getCalculatedWorkingTime());
                newStepDefinition.setFreeText(stepToCopy.getFreeText());
                stepDefinitionRepository.save(newStepDefinition);
            }
        }
        } catch (Exception e) {
        }
        return workPlan;
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
        WorkPlanWithStepsDTO wp = null;
        wp = new WorkPlanWithStepsDTO(this.getWorkplanById(id),
                stepDefinitionRepository.findByWorkPlan(id).stream()
                        .map(step -> new StepDefinitionDTO(step, operationService.get(step.getOperation())))
                        .collect(Collectors.toList()));
        if (wp.getWorkPlan() == null)
            wp = null;
        return wp;
    }

    public Long getWorkPlanTypeByWorkPlanNumber(Long workPlanNumber) {
        try {
            return workPlanRepository.findById(workPlanNumber).get().getWorkPlanType();
        } catch (Exception e) {

        }
        return 0l;
    }
}