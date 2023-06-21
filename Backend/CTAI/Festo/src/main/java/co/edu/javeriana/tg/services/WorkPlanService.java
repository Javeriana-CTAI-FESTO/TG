package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.WorkPlanDefinition;
import co.edu.javeriana.tg.entities.WorkPlanType;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.repositories.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.WorkPlanTypeRepository;

@Service
public class WorkPlanService {

    private final WorkPlanDefinitionRepository workPlanRepository;

    private final WorkPlanTypeRepository workPlanTypeRepository;

    private final OrderService orderService;

    private final StepService stepService;

    public WorkPlanService(WorkPlanDefinitionRepository workPlanRepository,
            WorkPlanTypeRepository workPlanTypeRepository, OrderService orderService, StepService stepService) {
        this.workPlanRepository = workPlanRepository;
        this.workPlanTypeRepository = workPlanTypeRepository;
        this.orderService = orderService;
        this.stepService = stepService;
    }

    public List<WorkPlanDTO> getAll() {
        return workPlanRepository.findAll().stream().map(WorkPlanDTO::new).collect(Collectors.toList());
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
            workplanEntity.setWorkPlanType(workPlanTypeRepository.findById(createRequest.getWorkPlanType()).get());
            workplanEntity.setDescription(createRequest.getDescription());
            workplanEntity.setShortDescription(createRequest.getShortDescription());
            workplanEntity.setPictureNumber(createRequest.getPictureNumber());
            workplanEntity.setPartNumber(createRequest.getPartNumber());
            workPlanRepository.save(workplanEntity);
            workPlan = new WorkPlanDTO(workplanEntity);
            for (int i = 1; i <= createRequest.getOperations().length; i++) {
                stepService.saveStep(workplanEntity, Long.valueOf(i), createRequest.getOperations()[i].getDescription(),
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

    public List<WorkPlanDTO> getWorkPlansByType(Long type) {
        return workPlanRepository.findByWorkPlanTypeTypeNumber(type).stream().map(WorkPlanDTO::new)
                .collect(Collectors.toList());
    }

    public OrderDTO generateNewOrder(Long workPlanNumber, Long clientNumber, Long positions) {
        return orderService.generateNewOrder(workPlanNumber, clientNumber, positions);
    }

    public WorkPlanDTO getById(Long id) {
        return new WorkPlanDTO(workPlanRepository.findById(id).get());
    }
}
