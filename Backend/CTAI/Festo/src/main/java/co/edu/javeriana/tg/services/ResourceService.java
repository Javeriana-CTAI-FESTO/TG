package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceDTO> getAll() {
        return resourceRepository.findAll().stream().map(ResourceDTO::new).collect(Collectors.toList());
    }

    public ResourceDTO getById(Long resource) {
        ResourceDTO result = null;
        try {
            result = new ResourceDTO(resourceRepository.findById(resource).get());
        } catch (Exception e) {
        }
        return result;
    }
}
