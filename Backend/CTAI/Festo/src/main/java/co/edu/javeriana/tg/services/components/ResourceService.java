package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@Component
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceDTO> getAllResources() {
        return resourceRepository.findAll().stream().map(ResourceDTO::new).collect(Collectors.toList());
    }

    public ResourceDTO getResourceById(Long resource) {
        ResourceDTO result = null;
        try {
            result = new ResourceDTO(resourceRepository.findById(resource).get());
        } catch (Exception e) {
        }
        return result;
    }
}
