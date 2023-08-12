package co.edu.javeriana.tg.integration.services;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.OperationToPerformByWorkPlanAux;
import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import co.edu.javeriana.tg.entities.managed.WorkPlanType;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.WorkPlanService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class WorkPlanServiceTest {
    @Autowired
    private WorkPlanService workPlanService;

    @MockBean
    private WorkPlanDefinitionRepository workPlanRepository;

    @MockBean
    private WorkPlanTypeRepository workPlanTypeRepository;

    @MockBean
    private OperationService operationService;

    @MockBean
    private StepDefinitionRepository stepDefinitionRepository;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, workPlanService.getAll().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        Long id = 1l;
        WorkPlanType t = new WorkPlanType(id);
        WorkPlanDefinition w = new WorkPlanDefinition(id);
        w.setWorkPlanType(id);
        t.setDescription("Test");
        when(workPlanRepository.findAll()).thenReturn(List.of(w));
        when(workPlanTypeRepository.findByTypeNumber(id)).thenReturn(t);
        assertEquals(1, workPlanService.getAll().size());
    }

    @Test
    public void testEmptyGetTypes() {
        assertEquals(0, workPlanService.getTypes().size());
    }

    @Test
    public void testNonEmptyGetTypes() {
        String description = "Test";
        Long id = 1l;
        WorkPlanType t = new WorkPlanType(id);
        t.setDescription(description);
        when(workPlanTypeRepository.findAll()).thenReturn(List.of(t));
        assertEquals(description, workPlanService.getTypes().get(id));
    }

    @Test
    public void testEmptyCreateWorkPlan() {
        assertNull(workPlanService.createWorkPlan(new CreateWorkPlanAux()));
    }

    @Test
    public void testNonEmptyCreateWorkPlan() {
        Long id = 1l;
        CreateWorkPlanAux plan = new CreateWorkPlanAux();
        plan.setWorkPlanNumber(id);
        plan.setWorkPlanType(id);
        plan.setDescription("Test");
        plan.setShortDescription("T");
        plan.setPictureNumber(1l);
        plan.setPartNumber(1l);
        plan.setOperations(new OperationToPerformByWorkPlanAux[0]);
        WorkPlanType t = new WorkPlanType(id);
        t.setDescription("Test");
        WorkPlanDefinition w = new WorkPlanDefinition(id);
        w.setWorkPlanType(id);
        when(workPlanTypeRepository.findByTypeNumber(id)).thenReturn(t);
        assertNotNull(workPlanService.createWorkPlan(plan));
    }

    @Test
    public void testNonEmptyCreateWorkPlanWithOperations() {
        Long id = 1l;
        CreateWorkPlanAux plan = new CreateWorkPlanAux();
        plan.setWorkPlanNumber(id);
        plan.setWorkPlanType(id);
        plan.setDescription("Test");
        plan.setShortDescription("T");
        plan.setPictureNumber(1l);
        plan.setPartNumber(1l);
        OperationToPerformByWorkPlanAux[] aux = {
                new OperationToPerformByWorkPlanAux(-1l, -1l, -1l,
                        "", -1l, -1l, false, -1l,
                        -1l, -1l, false, "", -1l,
                        -1l, -1l, "") };
        plan.setOperations(aux);
        WorkPlanType t = new WorkPlanType(id);
        t.setDescription("Test");
        WorkPlanDefinition w = new WorkPlanDefinition(id);
        w.setWorkPlanType(id);
        when(workPlanTypeRepository.findByTypeNumber(id)).thenReturn(t);
        assertNotNull(workPlanService.createWorkPlan(plan));
    }

    @Test
    public void testSaveStep() {
        assertDoesNotThrow(
                () -> workPlanService.saveStep(-1l, -1l, "", -1l, -1l, -1l, -1l, -1l, -1l, -1l, "", -1l, -1l, -1l, ""));
        assertDoesNotThrow(
                () -> workPlanService.saveStep(-1l, 1l, "", -1l, -1l, -1l, -1l, -1l, -1l, -1l, "", -1l, -1l, -1l, ""));
        when(operationService.get(-1l)).thenThrow(RuntimeException.class);
        assertDoesNotThrow(
                () -> workPlanService.saveStep(-1l, 1l, "", -1l, -1l, -1l, -1l, -1l, -1l, -1l, "", -1l, -1l, -1l, ""));
        assertNull(workPlanService.saveStep(-1l, 1l, "", -1l, -1l, -1l, -1l, -1l, -1l, -1l, "", -1l, -1l, -1l, ""));
    }

    @Test
    public void testEmptyGetWorkPlansByType() {
        Long id = 1l;
        assertEquals(0, workPlanService.getWorkPlansByType(id).size());
    }

    @Test
    public void testNonEmptyGetWorkPlansByType() {
        Long id = 1l;
        WorkPlanType t = new WorkPlanType(id);
        WorkPlanDefinition w = new WorkPlanDefinition(id);
        w.setWorkPlanType(id);
        t.setDescription("Test");
        when(workPlanRepository.findByWorkPlanType(id)).thenReturn(List.of(w));
        when(workPlanTypeRepository.findByTypeNumber(id)).thenReturn(t);
        assertEquals(1, workPlanService.getWorkPlansByType(id).size());
    }

    @Test
    public void testEmptyGetById() {
        Long id = 1l;
        assertNull(workPlanService.getById(id));
    }

    @Test
    public void testNonEmptyGetById() {
        Long id = 1l;
        WorkPlanType t = new WorkPlanType(id);
        t.setDescription("Test");
        WorkPlanDefinition w = new WorkPlanDefinition(id);
        w.setWorkPlanType(id);
        when(workPlanRepository.findById(id)).thenReturn(Optional.of(w));
        when(workPlanTypeRepository.findByTypeNumber(id)).thenReturn(t);
        assertNotNull(workPlanService.getById(id));
    }
}
