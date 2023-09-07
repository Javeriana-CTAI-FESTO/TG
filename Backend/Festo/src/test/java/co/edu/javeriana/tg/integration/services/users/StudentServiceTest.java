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

import co.edu.javeriana.tg.services.components.OrderService;
import co.edu.javeriana.tg.services.components.PartService;
import co.edu.javeriana.tg.services.components.WorkPlanService;
import co.edu.javeriana.tg.services.users.StudentService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class StudentServiceTest {
  @Autowired
  private StudentService studentService;
  
  @MockBean
  private OrderService orderService;

  @MockBean
  private PartService partService;

  @MockBean
  private WorkPlanService workPlanService;

  @Test
  public void testGetOrdersWithStatus() {
    assertDoesNotThrow(() -> studentService.getOrdersWithStatus());
  }

  @Test
  public void testGetOrdersWithPlannedEnd() {
    assertDoesNotThrow(() -> studentService.getAllOrdersPlannedEnds());
  }

  @Test
  public void testGetOrdersStatus() {
    assertDoesNotThrow(() -> studentService.getOrdersPossibleStatus());
  }

  @Test
  public void testGetOrdersFilterByStatus() {
    assertDoesNotThrow(() -> studentService.filterOrdersByStatus(1l));
  }

  @Test
  public void testGetOrdersWithTime() {
    assertDoesNotThrow(() -> studentService.getOrdersWithTime());
  }

  @Test
  public void testGetParts() {
    assertDoesNotThrow(() -> studentService.getAllParts());
  }

  @Test
  public void testGetPartsTypes() {
    assertDoesNotThrow(() -> studentService.getAllPartsTypes());
  }

  @Test
  public void testGetPartsByType() {
    assertDoesNotThrow(() -> studentService.getAllPartsByType(1l));
  }

  @Test
  public void testGetWorkplansById() {
    assertDoesNotThrow(() -> studentService.getWorkPlanById(1l));
  }

  @Test
  public void testGetPartsThatCanBeProduced() {
    assertDoesNotThrow(() -> studentService.getPartsThatCanBeProduced());
  }

  @Test
  public void testGetProductionIndicators() {
    assertDoesNotThrow(() -> studentService.getProductionIndicators());
  }

  @Test
  public void testGenerateNewOrder(){
    assertDoesNotThrow(() -> studentService.generateNewOrder(1l, 0l, 1l));
  }
}
