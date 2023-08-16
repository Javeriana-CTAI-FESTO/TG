package co.edu.javeriana.tg.integration.services.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.entities.managed.FinishedOrder;
import co.edu.javeriana.tg.entities.managed.Operation;
import co.edu.javeriana.tg.entities.managed.Order;
import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;
import co.edu.javeriana.tg.repositories.interfaces.FinishedOrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.services.components.ClientService;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.StepService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private FinishedOrderRepository finishedOrderRepository;

    @MockBean
    private OrderPositionRepository orderPositionRepository;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ResourceForOperationRepository resourceForOperationRepository;

    @MockBean
    private StepService stepService;

    @MockBean
    private PartService partService;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, orderService.getAllOrders().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(1L)));
        assertEquals(1, orderService.getAllOrders().size());
    }

    @Test
    public void testEmptyGetAllPlannedEnds() {
        assertEquals(0, orderService.getAllOrdersPlannedEnds().size());
    }

    @Test
    public void testNonEmptyGetAllPlanedEnds() {
        when(orderPositionRepository.findPlannedEnd()).thenReturn(List.of(new Date()));
        assertEquals(1, orderService.getAllOrdersPlannedEnds().size());
    }

    @Test
    public void testEmptyGenerateNewOrder() {
        assertNull(orderService.generateNewOrder(1l, 0l, 2l));
    }

    @Test
    public void testNonEmptyGenerateNewOrderWithEmptyOperations() {
        Long workPlanNumber = 1l;
        Long clientNumber = 1L;
        Long positions = 1L;
        when(stepService.getWorkPlanOperationsCount(workPlanNumber)).thenReturn(0l);
        assertNull(orderService.generateNewOrder(workPlanNumber,clientNumber ,positions ));
    }

    @Test
    public void testNonEmptyGenerateNewOrderOperations() {
        Long workPlanNumber = 1l;
        Long clientNumber = 1L;
        Long positions = 1L;
        Long lastONumber = 0l;
        when(stepService.getWorkPlanOperationsCount(workPlanNumber)).thenReturn(1l);
        when(orderRepository.getAllOrderNumbers()).thenReturn(List.of(lastONumber));
        when(partService.getWorkPlanNumberByPart(workPlanNumber)).thenReturn(workPlanNumber);
        StepDefinitionDTO firstStep = new StepDefinitionDTO();
        firstStep.setStepNumber(lastONumber);
        firstStep.setOperation(new OperationDTO(new Operation(lastONumber)));
        when(stepService.firstStepByWorkplan(workPlanNumber)).thenReturn(firstStep);
        when(clientService.getClientByClientNumber(clientNumber)).thenReturn(new ClientDTO(new Client(clientNumber)));
        when(resourceForOperationRepository.minorTimeOperation(firstStep.getOperation().getOperationNumber())).thenReturn(new ResourceForOperation(new ResourceForOperationPK(positions, firstStep.getOperation().getOperationNumber())));
        OrderDTO order = orderService.generateNewOrder(workPlanNumber, clientNumber, positions);
        assertEquals(lastONumber + 1, order.getOrderNumber());
    }

    @Test
    public void testNonEmptyGenerateNewOrderNoOrderNumbers() {
        Long workPlanNumber = 1l;
        Long clientNumber = 1L;
        Long positions = 1L;
        Long stepNumber = 0l;
        when(stepService.getWorkPlanOperationsCount(workPlanNumber)).thenReturn(1l);
        when(partService.getWorkPlanNumberByPart(workPlanNumber)).thenReturn(workPlanNumber);
        StepDefinitionDTO firstStep = new StepDefinitionDTO();
        firstStep.setStepNumber(stepNumber);
        firstStep.setOperation(new OperationDTO(new Operation(stepNumber)));
        when(stepService.firstStepByWorkplan(workPlanNumber)).thenReturn(firstStep);
        when(clientService.getClientByClientNumber(clientNumber)).thenReturn(new ClientDTO(new Client(clientNumber)));
        when(resourceForOperationRepository.minorTimeOperation(firstStep.getOperation().getOperationNumber())).thenReturn(new ResourceForOperation(new ResourceForOperationPK(positions, firstStep.getOperation().getOperationNumber())));
        OrderDTO order = orderService.generateNewOrder(workPlanNumber, clientNumber, positions);
        assertEquals(stepNumber + 1, order.getOrderNumber());
    }

    @Test
    public void testEmptyGetOrdersWithStatus() {
        assertEquals(0, orderService.getOrdersWithStatus().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithStatus() {
        Order order = new Order(1L);
        when(orderRepository.findAll()).thenReturn(List.of(order));
        assertEquals("Unstarted", orderService.getOrdersWithStatus().get(0).getStatus());
        order.setRealStart(Date.from(Instant.now()));
        assertEquals("In process", orderService.getOrdersWithStatus().get(0).getStatus());
        order.setRealEnd(Date.from(Instant.now()));
        assertEquals("Finished", orderService.getOrdersWithStatus().get(0).getStatus());
    }

    @Test
    public void testEmptyGetOrdersWithTime() {
        assertEquals(0, orderService.getOrdersWithTime().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithTime() {
        Order o = new Order(1L);
        o.setClientNumber(1l);
        when(orderRepository.findAll()).thenReturn(List.of(o));
        when(clientService.getClientByClientNumber(1l)).thenReturn(new ClientDTO(new Client(1l)));
        when(stepService.getWorkPlanTime(1l)).thenReturn(new WorkPlanTimeAux(1l, List.of(1l)));
        ResourceForOperation resourceForOperation = new ResourceForOperation(new ResourceForOperationPK(1l, 1l));
        resourceForOperation.setWorkingTime(1l);
        when(resourceForOperationRepository.minorTimeOperation(1l)).thenReturn(resourceForOperation);
        when(orderPositionRepository.countByOrder(1l)).thenReturn(0L);
        assertEquals(1, orderService.getOrdersWithTime().size());
        assertEquals("0s",orderService.getOrdersWithTime().get(0).getTimeNeeded()); 
    }

    @Test
    public void testEmptyGetPossibleStatus() {
        assertEquals(3, orderService.getPossibleStatus().size());
    }

    @Test
    public void testNonEmptyGetPossibleStatus() {

        assertEquals("Unstarted", orderService.getPossibleStatus().get(1l));
    }

    @Test
    public void testEmptyFilterByStatus() {
        assertNull(orderService.filterByStatus(-1l));
        assertNull(orderService.filterByStatus(4l));
    }

    @Test
    public void testNonEmptyFilterByStatusUnstarted() {
        Long status = 1l;
        Order unstarted = new Order(1l);
        Order inProcess = new Order(2l);
        FinishedOrder finished = new FinishedOrder(3l);
        when(finishedOrderRepository.finishedOrders()).thenReturn(List.of(finished));
        when(orderRepository.inProcessOrders()).thenReturn(List.of(inProcess));
        when(orderRepository.notStartedOrders()).thenReturn(List.of(unstarted));
        try {
            assertEquals(1l, orderService.filterByStatus(status).get(0).getOrderNumber());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyFilterByStatusInProcess() {
        Long status = 2l;
        Order unstarted = new Order(1l);
        Order inProcess = new Order(2l);
        FinishedOrder finished = new FinishedOrder(3l);
        when(finishedOrderRepository.finishedOrders()).thenReturn(List.of(finished));
        when(orderRepository.inProcessOrders()).thenReturn(List.of(inProcess));
        when(orderRepository.notStartedOrders()).thenReturn(List.of(unstarted));
        try {
            assertEquals(2l, orderService.filterByStatus(status).get(0).getOrderNumber());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyFilterByStatusFinished() {
        Long status = 3l;
        Order unstarted = new Order(1l);
        Order inProcess = new Order(2l);
        FinishedOrder finished = new FinishedOrder(3l);
        when(finishedOrderRepository.finishedOrders()).thenReturn(List.of(finished));
        when(orderRepository.inProcessOrders()).thenReturn(List.of(inProcess));
        when(orderRepository.notStartedOrders()).thenReturn(List.of(unstarted));
        try {
            assertEquals(3l, orderService.filterByStatus(status).get(0).getOrderNumber());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetIndicators() {
        assertEquals(0, orderService.getIndicators().get(0).getIndicatorValue());
    }

    @Test
    public void testNonEmptyGetIndicators() {
        when(orderRepository.notStartedOrdersCount()).thenReturn(1l);
        for (IndicatorAux aux:orderService.getIndicators()){
            if (aux.getIndicatorName().startsWith("Unstarted"))
                assertEquals(1, aux.getIndicatorValue());
        }
    }

    @Test
    public void testEmptyTimeForOrder(){
        assertNull(orderService.timeForOrder(1l));
    }
}