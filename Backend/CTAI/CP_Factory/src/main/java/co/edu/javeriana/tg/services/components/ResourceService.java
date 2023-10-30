package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@Component
@Transactional
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
            Optional<Resource> optionalResource = resourceRepository.findById(resource);
            if (optionalResource.isPresent())
                result = new ResourceDTO(optionalResource.get());
        } catch (Exception e) {
        }
        return result;
    }
}
