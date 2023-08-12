package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;

import java.util.List;


public interface ResourceForOperationRepository extends CrudRepository<ResourceForOperation, ResourceForOperationPK>{
    List<ResourceForOperation> findAll();
    List<ResourceForOperation> findByOperation(Long operationNumber);
    List<ResourceForOperation> findByResource(Long resourceId);

    @Query("select r from ResourceForOperation r where r.operation = ?1 and r.resource > 0 order by r.workingTime")
    ResourceForOperation minorTimeOperation(Long operationNumber);
}
