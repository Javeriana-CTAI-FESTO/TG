package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.repositories.interfaces.OperationRepository;

@Service
public class OperationService {
    
    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationDTO> getAll() {
        return operationRepository.findAll().stream().map(OperationDTO::new).collect(Collectors.toList());
    }

    public OperationDTO get(Long operationNumber){
        OperationDTO operation=null;
        try{
            operation = new OperationDTO(operationRepository.findById(operationNumber).get());
        } catch (Exception e){

        }
        return operation;
    }
}
