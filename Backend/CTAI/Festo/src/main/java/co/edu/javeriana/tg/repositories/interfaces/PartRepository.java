package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Part;

public interface PartRepository extends CrudRepository<Part, Long> {
    List<Part> findAll();

    List<Part> findByType(Long typeId);

    @Query("select p from Part p where p.Type = 3")
    List<Part> getAllProductionParts();
}
