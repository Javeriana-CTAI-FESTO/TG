package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.AutomaticOrder;

public interface AutomaticOrderRepository extends CrudRepository<AutomaticOrder, Long>{
    List<AutomaticOrder> findAll();
}
