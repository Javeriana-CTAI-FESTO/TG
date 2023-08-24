package co.edu.javeriana.tg.integration.services.users;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.services.components.OperationService;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.ResourceForOperationService;
import co.edu.javeriana.tg.services.components.ResourceService;
import co.edu.javeriana.tg.services.components.StepService;
import co.edu.javeriana.tg.services.components.WorkPlanService;
import co.edu.javeriana.tg.services.users.TeacherService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class TeacherServiceTest {
  @Autowired
  private TeacherService teacherService;

  @MockBean
  private OrderService orderService;

  @MockBean
  private WorkPlanService workPlanService;

  @MockBean
  private OperationService operationService;

  @MockBean
  private PartService partService;

  @MockBean
  private StepService stepService;

  @MockBean
  private ResourceForOperationService resourceForOperationService;

  @MockBean
  private ResourceService resourceService;

  @Test
  public void testGetProductionIndicators() {
    assertDoesNotThrow(() -> teacherService.getProductionIndicators());
  }

  @Test
  public void testGetParts() {
    assertDoesNotThrow(() -> teacherService.getAllParts());
  }

  @Test
  public void testGetPartsAvailable() {
    assertDoesNotThrow(() -> teacherService.getAllPartsAvailable());
  }

  @Test
  public void testGetPartsUnavailable() {
    assertDoesNotThrow(() -> teacherService.getAllPartsUnavailable());
  }

  @Test
  public void testGetPartsTypes() {
    assertDoesNotThrow(() -> teacherService.getAllPartsTypes());
  }

  @Test
  public void testGetPartsByType() {
    assertDoesNotThrow(() -> teacherService.getAllPartsByType(1l));
  }

  @Test
  public void testGetPartsThatCanBeProduced() {
    assertDoesNotThrow(() -> teacherService.getPartsThatCanBeProduced());
  }

  @Test
  public void testGetWorkplans() {
    assertDoesNotThrow(() -> teacherService.getAllWorkPlans());
  }

  @Test
  public void testGetWorkplansById() {
    assertDoesNotThrow(() -> teacherService.getWorkPlanById(1l));
  }

  @Test
  public void testGetWorkplansTypes() {
    assertDoesNotThrow(() -> teacherService.getAllWorkPlansTypes());
  }

  @Test
  public void testGetWorkplansByType() {
    assertDoesNotThrow(() -> teacherService.getWorkPlansByType(1l));
  }

  @Test
  public void testGetStepsDefined() {
    assertDoesNotThrow(() -> teacherService.getAllStepsDefined());
  }

  @Test
  public void testGetOperations() {
    assertDoesNotThrow(() -> teacherService.getAllOperations());
  }

  @Test
  public void testCreateWorkplan() {
    assertDoesNotThrow(() -> teacherService.createWorkPlan(new CreateWorkPlanAux()));
  }

  @Test
  public void testGetOrdersWithStatus() {
    assertDoesNotThrow(() -> teacherService.getOrdersWithStatus());
  }

  @Test
  public void testGetOrdersWithPlannedEnd() {
    assertDoesNotThrow(() -> teacherService.getAllOrdersPlannedEnds());
  }

  @Test
  public void testGetOrdersStatus() {
    assertDoesNotThrow(() -> teacherService.getOrdersPossibleStatus());
  }

  @Test
  public void testGetOrdersFilterByStatus() {
    assertDoesNotThrow(() -> teacherService.filterOrdersByStatus(1l));
  }

  @Test
  public void testGetPartsConsumedByOrders() {
    assertDoesNotThrow(() -> teacherService.getAllPartsConsumedByOrders());
  }

  @Test
  public void testGetOrdersWithTime() {
    assertDoesNotThrow(() -> teacherService.getOrdersWithTime());
  }

  @Test
  public void testGetOperationsForResource() {
    assertDoesNotThrow(() -> teacherService.getOperationsForResource(1l));
  }

  @Test
  public void testGetAllResources() {
    assertDoesNotThrow(() -> teacherService.getAllResources());
  }

  @Test
  public void testEnableOrder() {
    assertDoesNotThrow(() -> teacherService.enableOrder(1l));
  }
}
