package co.edu.javeriana.tg.integration.services;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.StepService;
import co.edu.javeriana.tg.services.WorkPlanService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class StepServiceTest {

    @Autowired
    private StepService stepService;

    @MockBean
    private StepDefinitionRepository stepDefinitionRepository;

    @MockBean
    private WorkPlanService workPlanService;

    @MockBean
    private OperationService operationService;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, stepService.getAll().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        Long workPlanNumber = 1l, stepNumber = 1l;
        when(stepDefinitionRepository.findAll()).thenReturn(List.of(new StepDefinition(new StepDefinitionPK(workPlanNumber, stepNumber))));
        assertEquals(1, stepService.getAll().size());
    }

    @Test
    public void testEmptyGetWorkPlanTime() {
        Long workPlanNumber = 1l;
        assertEquals(0, stepService.getWorkPlanTime(workPlanNumber).getTransportTime());
    }

    @Test
    public void testNonEmptyGetWorkPlanTime() {
        Long workPlanNumber = 1l, stepNumber = 1l;
        StepDefinition step = new StepDefinition(new StepDefinitionPK(workPlanNumber, stepNumber));
        step.setTransportTime(3L);
        when(stepDefinitionRepository.findByWorkPlan(workPlanNumber)).thenReturn(List.of(step));
        assertEquals(3l, stepService.getWorkPlanTime(workPlanNumber).getTransportTime());
    }

    @Test
    public void testEmptyGetWorkPlanOperationsCount() {
        Long workPlanNumber = 1l;
        assertEquals(0, stepService.getWorkPlanOperationsCount(workPlanNumber));
    }

    @Test
    public void testNonEmptyGetWorkPlanOperationsCount() {
        Long workPlanNumber = 1l, operations = 3l;
        when(stepDefinitionRepository.countByWorkPlan(workPlanNumber)).thenReturn(operations);
        assertEquals(operations, stepService.getWorkPlanOperationsCount(workPlanNumber));
    }
}
