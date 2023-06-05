package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.repositories.OperationRepository;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public List<OperationDTO> getAll() {
        return operationRepository.findAll().stream().map(OperationDTO::new).collect(Collectors.toList());
    }
}
