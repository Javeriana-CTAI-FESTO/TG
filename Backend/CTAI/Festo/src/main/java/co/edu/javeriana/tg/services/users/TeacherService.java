package co.edu.javeriana.tg.services.users;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.WorkPlanService;


@Service
public class TeacherService {
  
  private final WorkPlanService workPlanService;

  private final OrderService orderService;

  private final PartService partService;

  public TeacherService(WorkPlanService workPlanService, OrderService orderService, PartService partService) {
    this.workPlanService = workPlanService;
    this.orderService = orderService;
    this.partService = partService;
  }

  public List<PartDTO> getAllParts() {
    return partService.getAll();
  }

  public Map<Long, String> getAllPartsTypes() {
    return partService.getAllTypes();
  }

  public List<PartDTO> getAllPartsByType(Long typeId) {
    return partService.getAllByType(typeId);
  }

  public List<WorkPlanDTO> getAllWorkPlans() {
    return workPlanService.getAll();
  }

  public WorkPlanDTO getWorkPlanById(Long id) {
    return workPlanService.getById(id);
  }

  public Map<Long, String> getAllWorkPlansTypes() {
    return workPlanService.getTypes();
  }

  public List<WorkPlanDTO> getWorkPlansByType(Long typeId) {
    return workPlanService.getWorkPlansByType(typeId);
  }

  public WorkPlanDTO createWorkPlan(CreateWorkPlanAux createRequest) {
    return workPlanService.createWorkPlan(createRequest);
  }

  public List<OrderDTO> getOrdersWithStatus() {
    return orderService.getOrdersWithStatus();
  }

  public List<Date> getAllOrdersPlannedEnds() {
    return orderService.getAllPlannedEnds();
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
}
