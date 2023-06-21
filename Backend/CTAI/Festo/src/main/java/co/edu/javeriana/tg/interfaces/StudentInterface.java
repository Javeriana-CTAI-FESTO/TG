package co.edu.javeriana.tg.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;

public interface StudentInterface {
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans();

    public ResponseEntity<WorkPlanDTO> getWorkPlansById(Long id);

    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes();

    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(Long typeId);

    public ResponseEntity<OrderDTO> newOrder(Long workPlanNumber, Long clientNumber, Long positions);

    public ResponseEntity<List<OrderDTO>> getAllWorkPlanStatus();

    public ResponseEntity<List<Date>> getAllPlannedEnds();

    public ResponseEntity<List<OrderDTO>> getAllWorkPlanTime();

    public ResponseEntity<Map<Long, String>> getStatus();

    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(Long orderStatus);

    public ResponseEntity<List<IndicatorAux>> getAllIndicators();
}
