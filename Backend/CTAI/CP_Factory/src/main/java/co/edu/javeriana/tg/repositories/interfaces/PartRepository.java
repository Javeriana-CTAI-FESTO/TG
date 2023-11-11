package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Part;

public interface PartRepository extends CrudRepository<Part, Long> {
    List<Part> findAll();

    Part findByPartNumber(Long partNumber);

    List<Part> findByType(Long typeId);

    @Query("select p from Part p where p.type = 3")
    List<Part> getAllProductionParts();

    @Query("select p from Part p where p.lotSize > 0")
    List<Part> findAllAvailable();

    @Query("select p from Part p where p.lotSize = 0")
    List<Part> findAllUnavailable();

    @Query("select distinct type from Part")
    List<Long> findAllTypes();
}
