package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Part;

public interface PartRepository extends CrudRepository<Part, Long> {
    List<Part> findAll();
}
