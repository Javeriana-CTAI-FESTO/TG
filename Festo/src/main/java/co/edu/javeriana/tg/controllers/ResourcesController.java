package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.services.ResourceService;

@RestController
@RequestMapping("/api/resource")
public class ResourcesController {
    @Autowired
    private ResourceService resourceService;
    
    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAll() {
        List<ResourceDTO> resources = resourceService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<ResourceDTO>>(resources, status);
    }
}
