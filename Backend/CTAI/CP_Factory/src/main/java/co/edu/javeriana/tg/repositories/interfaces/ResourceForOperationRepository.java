package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;

import java.util.List;


public interface ResourceForOperationRepository extends CrudRepository<ResourceForOperation, ResourceForOperationPK>{
    List<ResourceForOperation> findAll();
    
    @Query("select r from ResourceForOperation r where r.operation = ?1")
    List<ResourceForOperation> findByOperation(Long operationNumber);

    @Query("select r from ResourceForOperation r where r.resource = ?1")
    List<ResourceForOperation> findByResource(Long resourceId);

    @Query("select r from ResourceForOperation r where r.operation = ?1 and r.resource > 0 order by r.workingTime")
    ResourceForOperation minorTimeForOperation(Long operationNumber);
}
