package co.edu.javeriana.tg.services.components;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.PartsConsumedByOrderDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.StepTimeDTO;
import co.edu.javeriana.tg.entities.managed.OperationParameter;
import co.edu.javeriana.tg.entities.managed.Order;
import co.edu.javeriana.tg.entities.managed.OrderPosition;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.Step;
import co.edu.javeriana.tg.repositories.interfaces.FinishedOrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.OperationParameterRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@Component
@Transactional
public class OrderService {

    private final OperationParameterRepository operationParameterRepository;

    private final OrderRepository orderRepository;

    private final FinishedOrderRepository finishedOrderRepository;

    private final OrderPositionRepository orderPositionRepository;

    private final ClientService clientService;

    private final ResourceForOperationRepository resourceForOperationRepository;

    private final ResourceRepository resourceRepository;

    private final StepService stepService;

    private final PartService partService;

    public OrderService(FinishedOrderRepository finishedOrderRepository, OrderRepository orderRepository,
            OrderPositionRepository orderPositionRepository,
            ClientService clientService, ResourceForOperationRepository resourceForOperationRepository,
            StepService stepService, PartService workPlanService, ResourceRepository resourceRepository,
            OperationParameterRepository operationParameterRepository) {
        this.finishedOrderRepository = finishedOrderRepository;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.clientService = clientService;
        this.resourceForOperationRepository = resourceForOperationRepository;
        this.stepService = stepService;
        this.partService = workPlanService;
        this.resourceRepository = resourceRepository;
        this.operationParameterRepository = operationParameterRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    public List<ZonedDateTime> getAllOrdersPlannedEnds() {
        return orderPositionRepository.findAll().stream().map(orderPosition -> orderPosition.getPlannedEnd().toInstant().atZone(ZoneId.of("America/Bogota"))).collect(Collectors.toList());
    }

    private List<OrderDTO> getAllFinishedOrders() {
        return finishedOrderRepository.finishedOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDTO> getAllUnstartedOrders() {
        return orderRepository.notStartedOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDTO> getAllInProcessOrders() {
        return orderRepository.inProcessOrders().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber())))
                .collect(Collectors.toList());
    }

    public OrderDTO generateNewOrder(Long partNumber, Long clientNumber, Long positions) {
        OrderDTO orderDTO = null;
        Long workPlanNumber = partService.getWorkPlanNumberByPart(partNumber);
        try {
            if (stepService.getWorkPlanOperationsCount(workPlanNumber) < 1)
                throw new Exception("Order has no steps");
            Order order = new Order();
            Date start = Date.from(Instant.now());
            Long orderNumber = getNextOrderNumber();
            order.setOrderNumber(orderNumber);
            order.setPlannedStart(start);
            Long secondsToAdd = timeForWorkPlan(workPlanNumber, positions);
            Date end = Date.from(Instant.now().plus(secondsToAdd, ChronoUnit.SECONDS));
            order.setPlannedEnd(end);
            order.setClientNumber(clientNumber);
            order.setState(0l);
            order.setEnabled(false);
            order.setRelease(end);
            orderRepository.save(order);
            OrderPosition orderPosition = null;
            List<StepDefinitionDTO> steps = stepService.stepsByWorkplan(workPlanNumber);
            for (Long position = 0L; position < positions; position++) {
                StepDefinitionDTO firstStep = steps.get(0);
                orderPosition = new OrderPosition();
                orderPosition.setOrderPosition(position + 1);
                orderPosition.setWorkPlanNumber(workPlanNumber);
                orderPosition.setPart(partNumber);
                orderPosition.setOrderPartNumber(partNumber);
                orderPosition.setOrder(order.getOrderNumber());
                orderPosition.setPlannedStart(start);
                orderPosition.setPlannedEnd(end);
                orderPosition.setMainOrderPosition(0l);
                orderPosition.setStepNumber(firstStep.getStepNumber());
                orderPosition.setState(0l);
                orderPosition.setOperationNumber(firstStep.getOperation().getOperationNumber());
                orderPosition.setResourceNumber(resourceForOperationRepository
                        .minorTimeForOperation(firstStep.getOperation().getOperationNumber()).getResource());
                orderPosition.setOperationNumber(firstStep.getOperation().getOperationNumber());
                orderPosition.setError(false);
                orderPosition.setSubOrderBlocked(false);
                orderPosition.setWorkOrderNumber(0L);
                orderPositionRepository.save(orderPosition);
                for (int ste = 0; ste < steps.size(); ste++) {
                    StepDefinitionDTO currentStep = steps.get(ste);
                    Step step = new Step();
                    step.setWorkPlanNumber(workPlanNumber);
                    step.setStepNumber(currentStep.getStepNumber());
                    step.setOrderNumber(orderNumber);
                    step.setOrderPosition(position + 1);
                    step.setDescription(currentStep.getDescription());
                    step.setOperation(currentStep.getOperation().getOperationNumber());
                    step.setFirstStep(ste == 0);
                    step.setNextWhenError(currentStep.getNextWhenError());
                    step.setNewPartNumber(currentStep.getNewPartNumber());
                    step.setPlannedStart(start);
                    secondsToAdd = currentStep.getCalculatedWorkingTime();
                    step.setPlannedEnd(Date.from(Instant.now().plus(secondsToAdd, ChronoUnit.SECONDS)));
                    step.setOperationNumberType(currentStep.getOperationNumberType());
                    ResourceForOperation res = resourceForOperationRepository
                            .minorTimeForOperation(steps.get(ste).getOperation().getOperationNumber());
                    step.setResource((res != null) ? res.getResource() : 0l);
                    step.setTransportTime(currentStep.getTransportTime());
                    step.setError(currentStep.getError());
                    step.setCalculatedElectricEnergy(0l);
                    step.setRealElectricEnergy(0l);
                    step.setCalculatedCompressedAir(currentStep.getCalculatedCompressedAir());
                    step.setRealCompressedAir(currentStep.getCalculatedCompressedAir());
                    step.setFreeText(currentStep.getFreeText());
                    if (ste < steps.size() - 1)
                        step.setNextStepNumber(steps.get(ste + 1).getStepNumber());
                    else
                        step.setNextStepNumber(0l);
                    stepService.saveStep(step);
                }
            }
            orderDTO = new OrderDTO(order, clientService.getClientByClientNumber(clientNumber));
        } catch (Exception e) {

        }
        return orderDTO;
    }

    private Long getNextOrderNumber() {
        try {
            Long numberExtractedOfUnfinishedOrders = orderRepository.getOrderNumbers().get(0);
            Long numberExtractedOfFinishedOrders = finishedOrderRepository.getOrderNumbers().get(0);
            return (numberExtractedOfFinishedOrders > numberExtractedOfUnfinishedOrders)
                    ? numberExtractedOfFinishedOrders + 1
                    : numberExtractedOfUnfinishedOrders + 1;
        } catch (Exception e) {
            return 1l;
        }
    }

    private String evaluateStatusForOrder(Order order) {
        String status = "Unstarted";
        if (order.getRealStart() != null && order.getRealEnd() == null)
            status = "In process";
        return status;
    }

    public List<OrderDTO> getOrdersWithStatus() {
        List<OrderDTO> orders = orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber()),
                        this.evaluateStatusForOrder(order)))
                .collect(Collectors.toList());
        orders.addAll(finishedOrderRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber()),
                        "Finished"))
                .collect(Collectors.toList()));
        return orders;
    }

    public List<OrderDTO> getOrdersWithTime() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber()),
                        this.timeForWorkPlan(
                                orderPositionRepository.getWorkPlanNumberByOrderNumber(order.getOrderNumber()),
                                orderPositionRepository.countByOrder(order.getOrderNumber()))))
                .collect(Collectors.toList());
    }

    public Map<Long, String> getPossibleStatus() {
        return Map.of(1L, "Unstarted", 2L, "In process", 3L, "Finished");
    }

    public List<OrderDTO> filterByStatus(Long status) {
        return (status > 3 || status < 0) ? null
                : (status == 1) ? getAllUnstartedOrders()
                        : (status == 2) ? getAllInProcessOrders() : getAllFinishedOrders();
    }

    private Double evaluateNameToGetIndicator(String name) {
        Long value;
        if (name.equals("Unstarted"))
            value = orderRepository.notStartedOrdersCount();
        else if (name.equals("In process"))
            value = orderRepository.inProcessOrdersCount();
        else if (name.equals("Finished"))
            value = finishedOrderRepository.finishedOrdersCount();
        else
            value = orderRepository.allOrdersCount();
        return Double.valueOf(value);
    }

    public IndicatorAux getAvailabilityForMachine(Long resource, String resource_name) {
        IndicatorAux aux = null;
        Long runtimeForMachine = stepService.getWorkTimeForMachine(resource);
        Long plannedTime = stepService.getPlannedWorkTimeForMachine(resource);
        if (plannedTime == 0l)
            plannedTime = runtimeForMachine + 1;
        aux = new IndicatorAux("Availability For " + resource_name,
                "This is the availability for resource " + resource_name, Double.parseDouble(
                        String.format("%.4f", Double.valueOf(runtimeForMachine) / Double.valueOf(plannedTime))));
        return aux;
    }

    public IndicatorAux getPerfomanceForMachine(Long resource, String resource_name) {
        IndicatorAux aux = null;
        Long runtimeForMachine = stepService.getWorkTimeForMachine(resource);
        Double idealCycleTime = stepService.getIdealWorkTimeForMachine(resource);
        Long totalCount = stepService.getTotalCountForMachine(resource);
        runtimeForMachine = (runtimeForMachine > 0) ? runtimeForMachine : 1l;
        aux = new IndicatorAux("Perfomance For " + resource_name,
                "This is the perfomance for resource " + resource_name,
                Double.parseDouble(String.format("%.4f",
                        Double.valueOf(idealCycleTime * totalCount) / Double.valueOf(runtimeForMachine))));
        return aux;
    }

    public List<StepTimeDTO> getStepsWithTimeByOrder(Long orderNumber) {
        return stepService.stepsWithTimeByOrder(orderNumber);
    }

    public IndicatorAux getQualityForMachine(Long resource, String resource_name) {
        IndicatorAux aux = null;
        // Machines don't reprocess, therefore quality is always 1
        aux = new IndicatorAux("Quality For " + resource_name, "This is the quality for resource " + resource_name,
                1.0);
        return aux;
    }

    public List<IndicatorAux> getOEEForMachine(Long resource) {
        // https://www.oee.com/calculating-oee/
        String resource_name = resourceRepository.findNameById(resource);
        IndicatorAux availability = this.getAvailabilityForMachine(resource, resource_name);
        IndicatorAux perfomance = this.getPerfomanceForMachine(resource, resource_name);
        IndicatorAux quality = this.getQualityForMachine(resource, resource_name);
        IndicatorAux oee = new IndicatorAux("OEE for machine " + resource_name,
                "Overall equipment efficiency for machine " + resource_name,
                availability.getIndicatorValue() * perfomance.getIndicatorValue() * quality.getIndicatorValue());
        return List.of(availability, perfomance, quality, oee);
    }

    public List<IndicatorAux> getIndicators() {
        final Map<String, String> indicators = Map.of("Unstarted", "This is the amount of unstarted orders",
                "In process", "This is the amount of in process orders", "Finished",
                "This is the amount of finished orders", "All",
                "This is the amount of orders processed by the system");
        List<IndicatorAux> ind = indicators.entrySet().stream()
                .map(entry -> new IndicatorAux(entry.getKey(), entry.getValue(),
                        this.evaluateNameToGetIndicator(entry.getKey())))
                .collect(Collectors.toList());
        try {
            Long totalResources = resourceRepository.findAll().stream().count();
            Double overallA = 0.0, overallP = 0.0, overallQ = 0.0, overallOEE = 0.0;

            List<IndicatorAux> indOee;
            for (Resource r : resourceRepository.findAllExceptZero()) {
                indOee = getOEEForMachine(r.getId());
                overallA += indOee.get(0).getIndicatorValue();
                overallP += indOee.get(1).getIndicatorValue();
                overallQ += indOee.get(2).getIndicatorValue();
                overallOEE += indOee.get(3).getIndicatorValue();
                ind.addAll(indOee);
            }
            overallA /= totalResources;
            overallA = Double.parseDouble(String.format("%.4f", overallA));
            overallP /= totalResources;
            overallP = Double.parseDouble(String.format("%.4f", overallP));
            overallQ /= totalResources;
            overallQ = Double.parseDouble(String.format("%.4f", overallQ));
            overallOEE /= totalResources;
            overallOEE = Double.parseDouble(String.format("%.4f", overallOEE));
            ind.add(new IndicatorAux("Availability", "This is the general availability of the system", overallA));
            ind.add(new IndicatorAux("Perfomance", "This is the general perfomance of the system", overallP));
            ind.add(new IndicatorAux("Quality", "This is the general quality of the system", overallQ));
            ind.add(new IndicatorAux("OEE", "This is the general efficiency of the system", overallOEE));
        } catch (Exception e) {
        }
        return ind;
    }

    public Long timeForWorkPlan(Long workPlanNumber, Long positions) {
        try {
            WorkPlanTimeAux auxiliaryWorkPlanTime = stepService.getWorkPlanTime(workPlanNumber);
            Long timeTakenByOperations = auxiliaryWorkPlanTime.getOperationsInvolved().stream()
                    .mapToLong(
                            operation -> {
                                ResourceForOperation res = resourceForOperationRepository
                                        .minorTimeForOperation(operation);
                                return (res != null) ? res.getWorkingTime() : 0l;
                            })
                    .sum();
            return (timeTakenByOperations + auxiliaryWorkPlanTime.getTransportTime())
                    * positions;
        } catch (Exception e) {

        }
        return 0l;
    }

    public List<PartsConsumedByOrderDTO> partsConsumedByOrders() {
        List<OrderDTO> orders = this.getAllOrders();
        return orders.stream()
                .map(order -> new PartsConsumedByOrderDTO(order, this.getPartsConsumedByOrder(order.getOrderNumber())))
                .collect(Collectors.toList());
    }

    public List<PartDTO> getPartsConsumedByOrder(Long orderNumber) {
        try {
            Long workPlanNumber = orderPositionRepository.getWorkPlanNumberByOrderNumber(orderNumber);
            List<StepDefinitionDTO> steps = stepService.stepsByWorkplan(workPlanNumber);
            Predicate<OperationParameter> partCondition1 = operationParameter -> operationParameter.getDescription()
                    .startsWith("part number");
            Predicate<OperationParameter> partCondition2 = operationParameter -> operationParameter.getDescription()
                    .startsWith("PNo");
            List<PartDTO> parts = steps.stream()
                    .flatMap(step -> operationParameterRepository
                            .findByOperationNumber(step.getOperation().getOperationNumber()).stream())
                    .filter(partCondition1.or(partCondition2)).filter(op -> !op.getParameter().equals("0"))
                    .map(operationParameter -> partService
                            .findByPartNumber(Long.valueOf(operationParameter.getParameter())))
                    .collect(Collectors.toList());
            return parts;
        } catch (Exception e) {
        }
        return null;
    }

    public OrderDTO enableOrder(Long orderNumber) {
        OrderDTO o = null;
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderNumber);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                order.setEnabled(true);
                orderRepository.save(order);
                o = new OrderDTO(order, clientService.getClientByClientNumber(order.getClientNumber()), "Starting ...");
            }
        } catch (Exception e) {

        }

        return o;
    }
}
