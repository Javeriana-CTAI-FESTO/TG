package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.managed.Operation;
import co.edu.javeriana.tg.repositories.interfaces.OperationRepository;

@Component
@Transactional
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationDTO> getAllOperations() {
        return operationRepository.findAll().stream().map(OperationDTO::new).collect(Collectors.toList());
    }

    public OperationDTO get(Long operationNumber) {
        OperationDTO operation = null;
        try {
            Optional<Operation> optionalOperation = operationRepository.findById(operationNumber);
            if (optionalOperation.isPresent())
                operation = new OperationDTO(optionalOperation.get());
        } catch (Exception e) {

        }
        return operation;
    }
}