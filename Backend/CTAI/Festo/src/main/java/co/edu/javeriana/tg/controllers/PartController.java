package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.services.PartService;

@RestController
@RequestMapping("/api/part")
public class PartController {
    @Autowired
    private PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDTO>> getAll() {
        List<PartDTO> resources = partService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }

}
