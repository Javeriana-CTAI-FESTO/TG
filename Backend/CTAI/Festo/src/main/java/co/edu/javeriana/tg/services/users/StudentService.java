package co.edu.javeriana.tg.services.users;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;


@Service
public class StudentService {
  
  private final OrderService orderService;

  private final PartService partService;

  public StudentService( OrderService orderService,  PartService partService) {
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

  public OrderDTO generateNewOrder(Long partNumber, Long clientNumber, Long positions) {
    return orderService.generateNewOrder(partNumber, clientNumber, positions);
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
    List<PartDTO> produceableParts = partService.getAllProductionProduceableParts();
    produceableParts.addAll(partService.getAllProductionProduceableParts());
    return produceableParts;
  }
}
