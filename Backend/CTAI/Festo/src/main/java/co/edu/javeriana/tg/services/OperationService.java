package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Operation;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.repositories.OperationRepository;

@Service
public class OperationService {
    
    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationDTO> getAll() {
        return operationRepository.findAll().stream().map(OperationDTO::new).collect(Collectors.toList());
    }

    Operation get(Long operationNumber){
        return operationRepository.findById(operationNumber).get();
    }
}
