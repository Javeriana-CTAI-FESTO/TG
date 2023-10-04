package co.edu.javeriana.tg.services.users;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.StepTimeDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.WorkPlanService;


@Service
@Transactional
public class StudentService {
  
  private final OrderService orderService;

  private final PartService partService;

  private WorkPlanService workPlanService;

  public StudentService( OrderService orderService,  PartService partService, WorkPlanService workPlanService) {
    this.orderService = orderService;
    this.partService = partService;
    this.workPlanService = workPlanService;
  }

  public List<PartDTO> getAllParts() {
    return partService.getAllAvailable();
  }

  public Map<Long, String> getAllPartsTypes() {
    return partService.getAllTypes();
  }

  public List<PartDTO> getAllPartsByType(Long typeId) {
    return partService.getAllByType(typeId);
  }

  public OrderDTO generateNewOrder(Long partNumber, Long clientNumber, Long positions) {
    return orderService.generateNewOrder(partNumber, clientNumber, positions);
  }

  public List<OrderDTO> getOrdersWithStatus() {
    return orderService.getOrdersWithStatus();
  }

  public List<ZonedDateTime> getAllOrdersPlannedEnds() {
    return orderService.getAllOrdersPlannedEnds();
  }

  public Map<Long, String> getOrdersPossibleStatus() {
    return orderService.getPossibleStatus();
  }

  public List<OrderDTO> filterOrdersByStatus(Long orderStatus) {
    return orderService.filterByStatus(orderStatus);
  }

  public List<OrderDTO> getOrdersWithTime() {
    return orderService.getOrdersWithTime();
  }

  public List<IndicatorAux> getProductionIndicators() {
    return orderService.getIndicators();
  }

  public List<StepTimeDTO> getStepsWithTimeByOrder(Long orderNumber) {
    return orderService.getStepsWithTimeByOrder(orderNumber);
  }

  public List<PartDTO> getPartsThatCanBeProduced() {
    return partService.getPartsThatCanBeProduced();
  }

  public WorkPlanWithStepsDTO getWorkPlanById(Long id) {
    return workPlanService.getWithStepsById(id);
  }
}
