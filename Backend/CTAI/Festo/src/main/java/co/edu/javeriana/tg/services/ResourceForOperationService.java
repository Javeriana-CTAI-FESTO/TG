package co.edu.javeriana.tg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;

@Service
public class ResourceForOperationService {
    
    private final ResourceForOperationRepository resourceForOperationRepository;

    private final StepService stepService;

    private final OperationService operationService;

    private final ResourceService resourceService;

    public ResourceForOperationService(ResourceForOperationRepository resourceForOperationRepository, StepService stepService, OperationService operationService, ResourceService resourceService) {
        this.resourceForOperationRepository = resourceForOperationRepository;
        this.stepService = stepService;
        this.operationService=operationService;
        this.resourceService=resourceService;
    }

    public ResourceForOperationDTO convertToDTO(List<ResourceForOperation> resources) {
        ResourceForOperationDTO resourceDTO = null;
        try {
            OperationDTO operation = operationService.get(resources.get(0).getOperation());
            List<ResourceDTO> resourceDTOs = resources.stream()
                    .map(resourceForOperation -> resourceService.getById(resourceForOperation.getResource()))
                    .collect(Collectors.toList());
            resourceDTO = new ResourceForOperationDTO(operation, resourceDTOs);
        } catch (Exception e) {
        }
        return resourceDTO;
    }

    public ResourceForOperationDTO getResourcesGivenOperation(Long operationNumber) {
        List<ResourceForOperation> resources = resourceForOperationRepository.findByOperation(operationNumber);
        return convertToDTO(resources);
    }

    public List<ResourceForOperationDTO> getResourcesGivenResource(Long resourceId) {
        List<ResourceForOperation> resources = resourceForOperationRepository.findByResource(resourceId);
        List<ResourceForOperation> operationResources = new ArrayList<>();
        List<ResourceForOperationDTO> operationResourcesDTOs = new ArrayList<>();
        Long operationToCompare = null;
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
                .mapToLong(operation -> resourceForOperationRepository.timeTakenByOperation(operation)).sum();
        return (timeTakenByOperations + auxiliaryWorkPlanTime.getTransportTime());
    }
}
