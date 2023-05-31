package co.edu.javeriana.tg.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.ResourceForOperation;
import co.edu.javeriana.tg.entities.ResourceForOperationPK;
import java.util.List;


public interface ResourceForOperationRepository extends CrudRepository<ResourceForOperation, ResourceForOperationPK>{
    List<ResourceForOperation> findByOperationId(Long operationNumber);
    List<ResourceForOperation> findByResourceId(Long resourceId);
}
