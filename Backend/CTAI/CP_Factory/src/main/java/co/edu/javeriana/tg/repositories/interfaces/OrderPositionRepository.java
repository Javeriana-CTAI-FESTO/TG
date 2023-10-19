package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.OrderPosition;
import co.edu.javeriana.tg.entities.managed.OrderPositionPK;

public interface OrderPositionRepository extends CrudRepository<OrderPosition, OrderPositionPK> {
    List<OrderPosition> findAll();
    Long countByOrder(Long orderNumber);
    @Query("select workPlanNumber from OrderPosition where order = ?1 and orderPosition = 1")
    Long getWorkPlanNumberByOrderNumber(Long orderNumber);
}
