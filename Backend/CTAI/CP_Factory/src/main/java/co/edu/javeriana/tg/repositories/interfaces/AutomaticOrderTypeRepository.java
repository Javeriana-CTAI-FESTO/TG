package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.AutomaticOrderType;

public interface AutomaticOrderTypeRepository extends CrudRepository<AutomaticOrderType, Long>{
    @Query("select auto_type from AutomaticOrderType auto_type")
    List<AutomaticOrderType> findAll();
}