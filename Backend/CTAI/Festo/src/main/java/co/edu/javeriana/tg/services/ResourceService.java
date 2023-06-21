package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.repositories.ResourceRepository;

@Service
public class ResourceService {
    
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceDTO> getAll() {
        return resourceRepository.findAll().stream().map(ResourceDTO::new).collect(Collectors.toList());
    }
}
