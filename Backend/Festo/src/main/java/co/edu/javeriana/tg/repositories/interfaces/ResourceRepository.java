package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long>{
    List<Resource> findAll();
    @Query("select name from Resource where id > 0")
    List<Resource> findAllExceptZero();
    @Query("select name from Resource where id = ?1")
    String findNameById(Long id);
}
