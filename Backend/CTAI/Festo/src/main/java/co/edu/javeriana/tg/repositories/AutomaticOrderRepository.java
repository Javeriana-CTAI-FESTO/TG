package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.AutomaticOrder;

public interface AutomaticOrderRepository extends CrudRepository<AutomaticOrder, Long>{
    List<AutomaticOrder> findAll();
}
