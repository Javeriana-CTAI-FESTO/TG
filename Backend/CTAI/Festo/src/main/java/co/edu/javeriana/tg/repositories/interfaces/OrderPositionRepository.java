package co.edu.javeriana.tg.repositories.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.OrderPosition;
import co.edu.javeriana.tg.entities.managed.OrderPositionPK;

public interface OrderPositionRepository extends CrudRepository<OrderPosition, OrderPositionPK> {
    List<OrderPosition> findAll();
    @Query("select o.plannedEnd from OrderPosition o")
    List<Date> findPlannedEnd();
    Long countByOrder(Long orderNumber);
}
