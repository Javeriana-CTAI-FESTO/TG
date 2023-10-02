package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.FinishedOrder;

import java.util.List;


public interface FinishedOrderRepository extends CrudRepository<FinishedOrder, Long>{
    @Query("select finished from FinishedOrder finished")
    List<FinishedOrder> findAll();

    @Query("select count(o) from FinishedOrder o where o.realEnd is not null")
    Long finishedOrdersCount();

    @Query("select o from FinishedOrder o where o.realEnd is not null")
    List<FinishedOrder> finishedOrders();

    @Query("select o.orderNumber from FinishedOrder o order by o.orderNumber desc")
    List<Long> getOrderNumbers();
}
