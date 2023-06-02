package co.edu.javeriana.tg.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
}
