package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.ResourceForOperation;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.repositories.ResourceForOperationRepository;

@Service
public class ResourceForOperationService {
    @Autowired
    private ResourceForOperationRepository resourceForOperationRepository;

    public ResourceForOperationDTO convertToDTO(List<ResourceForOperation> resources){
        ResourceForOperationDTO resourceDTO = null;
        try{
            OperationDTO operation = new OperationDTO(resources.get(0).getOperation());
            List<ResourceDTO> resourceDTOs = resources.stream().map(resourceForOperation -> new ResourceDTO(resourceForOperation.getResource())).collect(Collectors.toList());
            resourceDTO = new ResourceForOperationDTO(operation, resourceDTOs);
        } catch (Exception e){}
        return resourceDTO;
    }

    public ResourceForOperationDTO getResourcesGivenOperation(Long operationNumber){
        List<ResourceForOperation> resources =  resourceForOperationRepository.findByOperationId(operationNumber);
        return convertToDTO(resources);
    }

    public ResourceForOperationDTO getResourcesGivenResource(Long resourceId){
        List<ResourceForOperation> resources =  resourceForOperationRepository.findByResourceId(resourceId);
        return convertToDTO(resources);
    }
}
