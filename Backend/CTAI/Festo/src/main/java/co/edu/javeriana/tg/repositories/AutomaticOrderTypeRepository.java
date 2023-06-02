package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.AutomaticOrderType;

public interface AutomaticOrderTypeRepository extends CrudRepository<AutomaticOrderType, Long>{
    List<AutomaticOrderType> findAll();
}