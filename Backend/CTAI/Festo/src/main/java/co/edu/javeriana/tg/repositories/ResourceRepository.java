package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long>{
    List<Resource> findAll();
}
