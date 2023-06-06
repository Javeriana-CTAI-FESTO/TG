package co.edu.javeriana.tg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Operation;
import co.edu.javeriana.tg.entities.ResourceForOperation;
import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.repositories.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.ResourceForOperationRepository;

@Service
public class ResourceForOperationService {
    @Autowired
    private ResourceForOperationRepository resourceForOperationRepository;

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    @Autowired
    private StepService stepService;

    public ResourceForOperationDTO convertToDTO(List<ResourceForOperation> resources) {
        ResourceForOperationDTO resourceDTO = null;
        try {
            OperationDTO operation = new OperationDTO(resources.get(0).getOperation());
            List<ResourceDTO> resourceDTOs = resources.stream()
                    .map(resourceForOperation -> new ResourceDTO(resourceForOperation.getResource()))
                    .collect(Collectors.toList());
            resourceDTO = new ResourceForOperationDTO(operation, resourceDTOs);
        } catch (Exception e) {
        }
        return resourceDTO;
    }

    public ResourceForOperationDTO getResourcesGivenOperation(Long operationNumber) {
        List<ResourceForOperation> resources = resourceForOperationRepository.findByOperationId(operationNumber);
        return convertToDTO(resources);
    }

    public List<ResourceForOperationDTO> getResourcesGivenResource(Long resourceId) {
        List<ResourceForOperation> resources = resourceForOperationRepository.findByResourceId(resourceId);
        List<ResourceForOperation> operationResources = new ArrayList<>();
        List<ResourceForOperationDTO> operationResourcesDTOs = new ArrayList<>();
        Operation operationToCompare = null;
        boolean needToStartAgain = true;
        for (int i = 0; i < resources.size();) {
            if (needToStartAgain) {
                operationResources.clear();
                operationToCompare = resources.get(i).getOperation();
                needToStartAgain = false;
            }
            if (resources.get(i).getOperation().equals(operationToCompare)) {
                operationResources.add(resources.get(i));
                i++;
            } else {
                operationResourcesDTOs.add(convertToDTO(operationResources));
                needToStartAgain = true;
            }
        }
        return operationResourcesDTOs;
    }

    public Long timeForWorkPlan(Long workPlanNumber) {
        WorkPlanTimeAux auxiliaryWorkPlanTime = stepService.getWorkPlanTime(workPlanNumber);
        Long timeTakenByOperations = auxiliaryWorkPlanTime.getOperationsInvolved().stream()
                .mapToLong(operation -> resourceForOperationRepository.timeTakenByOperation(operation.getId())).sum();
        return (timeTakenByOperations + auxiliaryWorkPlanTime.getTransportTime());
    }

    public Long timeForOrder(Long orderNumber) {
        WorkPlanTimeAux auxiliaryWorkPlanTime = stepService.getWorkPlanTime(orderNumber);
        Long timeTakenByOperations = auxiliaryWorkPlanTime.getOperationsInvolved().stream()
                .mapToLong(operation -> resourceForOperationRepository.timeTakenByOperation(operation.getId())).sum();
        return (timeTakenByOperations + auxiliaryWorkPlanTime.getTransportTime()) * orderPositionRepository.countByOrderOrderNumber(orderNumber);
    }
}
