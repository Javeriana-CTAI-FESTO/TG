package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Order;

import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findAll();

    @Query("select count(o) from Order o")
    Long allOrdersCount();

    @Query("select o from Order o where o.realStart is null")
    List<Order> notStartedOrders();

    @Query("select count(o) from Order o where o.realStart is null")
    Long notStartedOrdersCount();

    @Query("select o from Order o where o.realStart is not null and o.realEnd is null")
    List<Order> inProcessOrders();

    @Query("select count(o) from Order o where o.realStart is not null and o.realEnd is null")
    Long inProcessOrdersCount();
    
    @Query("select o.orderNumber from FinishedOrder o order by o.orderNumber desc")
    List<Long> getOrderNumbers();
}
