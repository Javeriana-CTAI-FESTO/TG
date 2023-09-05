package co.edu.javeriana.tg.integration.services.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.managed.FinishedOrder;
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
    public void testNonEmptyGenerateNewOrder() {
        Long partNumber = 1l, clientNumber = 0l, positions = 1l, wp = 1l, orderN = 5L, finishedN = 2L;
        when(partService.getWorkPlanNumberByPart(partNumber)).thenReturn(wp);
        when(stepService.getWorkPlanOperationsCount(wp)).thenReturn(1l);
        when(orderRepository.getOrderNumbers()).thenReturn(List.of(orderN));
        when(finishedOrderRepository.getOrderNumbers()).thenReturn(List.of(finishedN));
        WorkPlanTimeAux aux = new WorkPlanTimeAux(wp, List.of(0l));
        when(stepService.getWorkPlanTime(wp)).thenReturn(aux);
        ResourceForOperation r = new ResourceForOperation();
        r.setWorkingTime(0l);
        when(resourceForOperationRepository.minorTimeForOperation(0l)).thenReturn(r);        
        assertNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        StepDefinitionDTO step = new StepDefinitionDTO();
        OperationDTO operation = new OperationDTO();
        Long opno = 1L, resource = 1L;
        operation.setOperationNumber(opno);
        step.setOperation(operation);
        when(stepService.stepsByWorkplan(wp)).thenReturn(List.of(step));
        when(resourceForOperationRepository.minorTimeForOperation(opno)).thenReturn(new ResourceForOperation(new ResourceForOperationPK(resource, opno)));
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        StepDefinitionDTO step2 = new StepDefinitionDTO();
        OperationDTO operation2 = new OperationDTO();
        Long op2no = 2L;
        operation2.setOperationNumber(op2no);
        step2.setOperation(operation2);
        when(resourceForOperationRepository.minorTimeForOperation(op2no)).thenReturn(new ResourceForOperation(new ResourceForOperationPK(resource, op2no)));
        when(stepService.stepsByWorkplan(wp)).thenReturn(List.of(step, step2));
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        when(resourceForOperationRepository.minorTimeForOperation(op2no)).thenReturn(null);
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        finishedN = 10L;
        when(finishedOrderRepository.getOrderNumbers()).thenReturn(List.of(finishedN));
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        when(orderRepository.getOrderNumbers()).thenThrow(RuntimeException.class);
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
        positions = 0l;
        assertNotNull(orderService.generateNewOrder(partNumber, clientNumber, positions));
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
    public void testEmptyGetOrdersWithStatus() {
        assertEquals(0, orderService.getOrdersWithStatus().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithStatus() {
        Order order = new Order(1L);
        FinishedOrder finishedOrder = new FinishedOrder(1l);
        when(orderRepository.findAll()).thenReturn(List.of(order));
        assertEquals("Unstarted", orderService.getOrdersWithStatus().get(0).getStatus());
        order.setRealStart(Date.from(Instant.now()));
        assertEquals("In process", orderService.getOrdersWithStatus().get(0).getStatus());
        order.setRealEnd(Date.from(Instant.now()));
        assertDoesNotThrow(() -> orderService.getOrdersWithStatus().get(0).getStatus());
        when(orderRepository.findAll()).thenReturn(new ArrayList<>(0));
        when(finishedOrderRepository.findAll()).thenReturn(List.of(finishedOrder));
        assertEquals("Finished", orderService.getOrdersWithStatus().get(0).getStatus());
    }

    @Test
    public void testEmptyGetOrdersWithTime() {
        assertEquals(0, orderService.getOrdersWithTime().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithTime() {
        assertNotNull(orderService.getOrdersWithTime());
        Long on = 1l,  wp = 1l, positions = 1l;
        WorkPlanTimeAux aux = new WorkPlanTimeAux(wp, List.of(0l));
        when(stepService.getWorkPlanTime(wp)).thenReturn(aux);
        ResourceForOperation r = new ResourceForOperation();
        r.setWorkingTime(0l);
        when(resourceForOperationRepository.minorTimeForOperation(0l)).thenReturn(r);
        Order o = new Order(on);
        o.setClientNumber(on);
        when(orderPositionRepository.getWorkPlanNumberByOrderNumber(on)).thenReturn(wp);
        when(orderPositionRepository.countByOrder(on)).thenReturn(positions);
        when(orderRepository.findAll()).thenReturn(List.of(o));
        assertNotNull(orderService.getOrdersWithTime());
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
        assertEquals(0l, orderService.timeForWorkPlan(1l, 1l));
    }

    @Test
    public void testTimeForWorkplan(){
        Long wp = 1l, positions = 1l;
        WorkPlanTimeAux aux = new WorkPlanTimeAux(wp, List.of(0l));
        when(stepService.getWorkPlanTime(wp)).thenReturn(aux);
        ResourceForOperation r = new ResourceForOperation();
        r.setWorkingTime(0l);
        when(resourceForOperationRepository.minorTimeForOperation(0l)).thenReturn(r);
        assertNotNull(orderService.timeForWorkPlan(wp, positions));
        when(resourceForOperationRepository.minorTimeForOperation(0l)).thenReturn(null);
        assertNotNull(orderService.timeForWorkPlan(wp, positions));
    }

    @Test
    public void testPartsConsumedByOrders(){ 
        assertDoesNotThrow(() -> orderService.partsConsumedByOrders());
        Long on = 1l;
        Order order = new Order();
        order.setOrderNumber(on);
        when(orderRepository.findAll()).thenReturn(List.of(order));
        assertDoesNotThrow(() -> orderService.partsConsumedByOrders());
    }

    @Test
    public void testEnableOrder(){
        Long on = 1l;
        Order order = new Order();
        order.setOrderNumber(on);
        order.setClientNumber(on);
        when(clientService.getClientByClientNumber(on)).thenReturn(null);
        when(orderRepository.findById(on)).thenReturn(Optional.of(order));
        assertTrue(orderService.enableOrder(on).getEnabled());
        when(orderRepository.findById(on)).thenThrow(RuntimeException.class);
        assertNull(orderService.enableOrder(on));
    }
}