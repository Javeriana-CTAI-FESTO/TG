package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.repositories.PartRepository;

@Service
public class PartService {
    @Autowired
    private PartRepository partRepository;

    public List<PartDTO> getAll() {
        return partRepository.findAll().stream().map(PartDTO::new).collect(Collectors.toList());
    }
}
