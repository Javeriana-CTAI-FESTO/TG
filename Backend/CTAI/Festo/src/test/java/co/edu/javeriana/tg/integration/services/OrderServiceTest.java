package co.edu.javeriana.tg.integration.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
import co.edu.javeriana.tg.entities.managed.Order;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.services.ClientService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.StepService;

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
    private OrderPositionRepository orderPositionRepository;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ResourceForOperationRepository resourceForOperationRepository;

    @MockBean
    private StepService stepService;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, orderService.getAll().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(1L)));
        assertEquals(1, orderService.getAll().size());
    }

    @Test
    public void testEmptyGetAllPlannedEnds() {
        assertEquals(0, orderService.getAllPlannedEnds().size());
    }

    @Test
    public void testNonEmptyGetAllPlanedEnds() {
        when(orderPositionRepository.findPlannedEnd()).thenReturn(List.of(new Date()));
        assertEquals(1, orderService.getAllPlannedEnds().size());
    }

    @Test
    public void testEmptyGenerateNewOrder() {
        assertNull(orderService.generateNewOrder(1l, 0l, 2l));
    }

    @Test
    public void testNonEmptyGenerateNewOrder() {
        Long testID = 1l;
        when(stepService.getWorkPlanOperationsCount(testID)).thenReturn(1l);
        when(orderRepository.getAllOrderNumbers()).thenReturn(List.of(2l, 1l));
        assertNotNull(orderService.generateNewOrder(testID, 0l, 2l));
    }

    @Test
    public void testEmptyGetOrdersWithStatus() {
        assertEquals(0, orderService.getOrdersWithStatus().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithStatus() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(1L)));
        assertEquals("Unstarted", orderService.getOrdersWithStatus().get(0).getStatus());
    }

    @Test
    public void testEmptyGetOrdersWithTime() {
        assertEquals(0, orderService.getOrdersWithTime().size());
    }

    @Test
    public void testNonEmptyGetOrdersWithTime() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(1L)));
        when (stepService.getWorkPlanTime(1l)).thenReturn(new WorkPlanTimeAux(1l, List.of(1l)));
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
        assertThrows(Exception.class, () -> orderService.filterByStatus(-1l));
    }

    @Test
    public void testNonEmptyFilterByStatus() {
        assertEquals("Unstarted", orderService.getPossibleStatus().get(1l));
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
}