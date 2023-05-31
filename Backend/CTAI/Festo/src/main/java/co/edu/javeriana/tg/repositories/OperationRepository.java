package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long>{
    public List<Operation> findAll();
}
