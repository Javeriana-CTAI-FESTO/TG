package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;

import java.util.List;


public interface ResourceForOperationRepository extends CrudRepository<ResourceForOperation, ResourceForOperationPK>{
    List<ResourceForOperation> findByOperation(Long operationNumber);
    List<ResourceForOperation> findByResource(Long resourceId);

    @Query("select sum(r.workingTime) from ResourceForOperation r where r.operation = ?1")
    Long timeTakenByOperation(Long operationNumber);
}
