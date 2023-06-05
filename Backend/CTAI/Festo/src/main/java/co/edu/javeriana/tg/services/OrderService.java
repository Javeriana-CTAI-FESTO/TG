package co.edu.javeriana.tg.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Order;
import co.edu.javeriana.tg.entities.OrderPosition;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.repositories.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ResourceForOperationService resourceForOperationService;

    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    public List<Date> getAllPlannedEnds() {
        return orderPositionRepository.findPlannedEnd();
    }

    private List<OrderDTO> getAllFinishedOrders() {
        return orderRepository.finishedOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDTO> getAllUnstartedOrders() {
        return orderRepository.notStartedOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDTO> getAllInProcessOrders() {
        return orderRepository.inProcessOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    public OrderDTO generateNewOrder(Long workPlanNumber, Long clientNumber) {
        OrderDTO orderDTO = null;
        try {
            Order order = new Order();
            Long orderNumber = getNextOrderNumber();
            order.setOrderNumber(orderNumber);
            order.setClientNumber(clientNumber);
            orderRepository.save(order);
            OrderPosition orderPosition = new OrderPosition();
            // No entiendo el OPos
            orderPosition.setOrderPosition(1L);
            orderPosition.setWorkPlanNumber(workPlanNumber);
            orderPosition.setOrder(order);
            orderPositionRepository.save(orderPosition);
            orderDTO = new OrderDTO(order, clientService.getClient(clientNumber));
        } catch (Exception e) {
        }
        return orderDTO;
    }

    private Long getNextOrderNumber() {
        return orderRepository.getAllOrderNumbers().get(0) + 1;
    }

    private String evaluateStatus(OrderPosition order) {
        String status;
        if (order.getRealStart() != null && order.getRealEnd() == null)
            status = "In process";
        else if (order.getRealStart() == null)
            status = "Unstarted";
        else
            status = "Finished";
        return status;
    }

    public List<OrderDTO> getOrdersWithStatus() {
        return orderPositionRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getOrder().getClientNumber()),
                        this.evaluateStatus(order)))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersWithTime() {
        return orderPositionRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClient(order.getOrder().getClientNumber()), resourceForOperationService.timeForWorkPlan(order.getWorkPlanNumber())))
                .collect(Collectors.toList());
    }

    public Map<Long, String> getPossibleStatus() {
        return Map.of(1L, "Unstarted", 2L, "In process", 3L, "Finished");
    }

    public List<OrderDTO> filterByStatus(Long status) throws Exception {
        if (status > 3 || status < 0)
            throw new Exception("Invalid");
        return (status == 1) ? getAllUnstartedOrders()
                : (status == 2) ? getAllInProcessOrders() : (status == 3) ? getAllFinishedOrders() : null;
    }

    private Long evaluateNameToGetIndicator(String name) {
        Long value;
        if (name.equals("Unstarted"))
            value = orderRepository.notStartedOrdersCount();
        else if (name.equals("In process"))
            value = orderRepository.inProcessOrdersCount();
        else if (name.equals("Finished"))
            value = orderRepository.finishedOrdersCount();
        else
            value = orderRepository.allOrdersCount();
        return value;
    }

    public List<IndicatorAux> getIndicators() {
        final Map<String, String> indicators = Map.of("Unstarted", "This is the amount of unstarted orders",
                "In process", "This is the amount of in process orders", "Finished",
                "This is the amount of finished orders", "All",
                "This is the amount of orders processed by the system");
        return indicators.entrySet().stream().map(entry -> new IndicatorAux(entry.getKey(), entry.getValue(),
                this.evaluateNameToGetIndicator(entry.getKey()))).collect(Collectors.toList());
    }
}
