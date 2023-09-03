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

import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.components.ClientService;
import co.edu.javeriana.tg.services.components.MachineReportService;
import co.edu.javeriana.tg.services.components.OperationService;
import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.ResourceForOperationService;
import co.edu.javeriana.tg.services.components.ResourceService;
import co.edu.javeriana.tg.services.components.StepService;
import co.edu.javeriana.tg.services.components.WorkPlanService;
import co.edu.javeriana.tg.services.users.AdminService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class AdminServiceTest {

  @Autowired
  private AdminService adminService;

  @MockBean
  private MachineReportService reportService;

  @MockBean
  private ClientService clientService;

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
  public void testGetAllReports() {
    assertDoesNotThrow(() -> adminService.getAllReports());
  }

  @Test
  public void testGetReportsForMachine() {
    assertDoesNotThrow(() -> adminService.getReportsForMachine(1l));
  }

  @Test
  public void testGetAllFails() {
    assertDoesNotThrow(() -> adminService.getAllFails());
  }

  @Test
  public void testGetFailsForMachine() {
    assertDoesNotThrow(() -> adminService.getAllFailsForMachine(1l));
  }

  @Test
  public void testGetAllClients() {
    assertDoesNotThrow(() -> adminService.getAllClients());
  }

  @Test
  public void testCreateClient() {
    assertDoesNotThrow(() -> adminService.createClient(new Client()));
  }

  @Test
  public void testGetProductionIndicators() {
    assertDoesNotThrow(() -> adminService.getProductionIndicators());
  }

  @Test
  public void testGetParts() {
    assertDoesNotThrow(() -> adminService.getAllParts());
  }

  @Test
  public void testGetPartsAvailable() {
    assertDoesNotThrow(() -> adminService.getAllPartsAvailable());
  }

  @Test
  public void testGetPartsUnavailable() {
    assertDoesNotThrow(() -> adminService.getAllPartsUnavailable());
  }

  @Test
  public void testGetPartsTypes() {
    assertDoesNotThrow(() -> adminService.getAllPartsTypes());
  }

  @Test
  public void testGetPartsByType() {
    assertDoesNotThrow(() -> adminService.getAllPartsByType(1l));
  }

  @Test
  public void testGetPartsThatCanBeProduced() {
    assertDoesNotThrow(() -> adminService.getPartsThatCanBeProduced());
  }

  @Test
  public void testGetWorkplans() {
    assertDoesNotThrow(() -> adminService.getAllWorkPlans());
  }

  @Test
  public void testGetWorkplansById() {
    assertDoesNotThrow(() -> adminService.getWorkPlanById(1l));
  }

  @Test
  public void testGetWorkplansTypes() {
    assertDoesNotThrow(() -> adminService.getAllWorkPlansTypes());
  }

  @Test
  public void testGetWorkplansByType() {
    assertDoesNotThrow(() -> adminService.getWorkPlansByType(1l));
  }

  @Test
  public void testGetStepsDefined() {
    assertDoesNotThrow(() -> adminService.getAllStepsDefined());
  }

  @Test
  public void testGetOperations() {
    assertDoesNotThrow(() -> adminService.getAllOperations());
  }

  @Test
  public void testCreateWorkplan() {
    assertDoesNotThrow(() -> adminService.createWorkPlan(new CreateWorkPlanAux()));
  }

  @Test
  public void testGetOrdersWithStatus() {
    assertDoesNotThrow(() -> adminService.getOrdersWithStatus());
  }

  @Test
  public void testGetOrdersWithPlannedEnd() {
    assertDoesNotThrow(() -> adminService.getAllOrdersPlannedEnds());
  }

  @Test
  public void testGetOrdersStatus() {
    assertDoesNotThrow(() -> adminService.getOrdersPossibleStatus());
  }

  @Test
  public void testGetOrdersFilterByStatus() {
    assertDoesNotThrow(() -> adminService.filterOrdersByStatus(1l));
  }

  @Test
  public void testGetPartsConsumedByOrders() {
    assertDoesNotThrow(() -> adminService.getAllPartsConsumedByOrders());
  }

  @Test
  public void testGetOrdersWithTime() {
    assertDoesNotThrow(() -> adminService.getOrdersWithTime());
  }

  @Test
  public void testCreatePart() {
    assertDoesNotThrow(() -> adminService.createPart(new CreatePartAux()));
  }

  @Test
  public void testGetOperationsForResource() {
    assertDoesNotThrow(() -> adminService.getOperationsForResource(1l));
  }

  @Test
  public void testGetAllResources() {
    assertDoesNotThrow(() -> adminService.getAllResources());
  }
}
