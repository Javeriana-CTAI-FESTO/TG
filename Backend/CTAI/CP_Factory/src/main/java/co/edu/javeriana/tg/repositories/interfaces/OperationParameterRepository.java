package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.OperationParameter;
import co.edu.javeriana.tg.entities.managed.OperationParameterPK;
import java.util.List;


public interface OperationParameterRepository extends CrudRepository<OperationParameter, OperationParameterPK>{
  List<OperationParameter> findByOperationNumber(Long operationNumber);
}
