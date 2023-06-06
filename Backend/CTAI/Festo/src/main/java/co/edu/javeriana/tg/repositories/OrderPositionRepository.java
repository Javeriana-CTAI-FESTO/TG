package co.edu.javeriana.tg.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.OrderPosition;
import co.edu.javeriana.tg.entities.OrderPositionPK;

public interface OrderPositionRepository extends CrudRepository<OrderPosition, OrderPositionPK> {
    List<OrderPosition> findAll();
    @Query("select o.plannedEnd from OrderPosition o")
    List<Date> findPlannedEnd();
    Long countByOrderOrderNumber(Long orderNumber);
}
