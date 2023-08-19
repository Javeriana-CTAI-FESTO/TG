package co.edu.javeriana.tg.services.users;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.PartsConsumedByOrderDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.services.components.OperationService;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.ResourceForOperationService;
import co.edu.javeriana.tg.services.components.ResourceService;
import co.edu.javeriana.tg.services.components.StepService;
import co.edu.javeriana.tg.services.components.WorkPlanService;


@Service
public class TeacherService {
  
  private final WorkPlanService workPlanService;

  private final OrderService orderService;

  private final PartService partService;

  private final StepService stepService;

  private final OperationService operationService;

  private final ResourceForOperationService resourceForOperationService;

  private final ResourceService resourceService;

  public TeacherService(WorkPlanService workPlanService, OrderService orderService, PartService partService, StepService stepService, OperationService operationService, ResourceForOperationService resourceForOperationService, ResourceService resourceService) {
    this.workPlanService = workPlanService;
    this.orderService = orderService;
    this.partService = partService;
    this.stepService = stepService;
    this.operationService = operationService;
    this.resourceForOperationService = resourceForOperationService;
    this.resourceService = resourceService;
  }

  public List<PartDTO> getAllParts() {
    return partService.getAll();
  }

  public List<PartDTO> getAllPartsUnavailable() {
    return partService.getAllUnavailable();
  }

  public List<PartDTO> getAllPartsAvailable() {
    return partService.getAllAvailable();
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

  public WorkPlanWithStepsDTO getWorkPlanById(Long id) {
    return workPlanService.getWithStepsById(id);
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

  public List<PartDTO> getPartsThatCanBeProduced() {
    return partService.getPartsThatCanBeProduced();
  }

  public List<PartsConsumedByOrderDTO> getAllPartsConsumedByOrders() {
    return orderService.partsConsumedByOrders();
  }

  public List<StepDefinitionDTO> getAllStepsDefined() {
    return stepService.getAll();
  }

  public List<OperationDTO> getAllOperations() {
    return operationService.getAllOperations();
  }

  public List<ResourceForOperationDTO> getOperationsForResource(Long resource) {
    return resourceForOperationService.getOperationsGivenResource(resource);
  }

  public List<ResourceDTO> getAllResources() {
      return resourceService.getAllResources();
    }

  public OrderDTO enableOrder(Long orderNumber) {
    return orderService.enableOrder(orderNumber);
  }
}
