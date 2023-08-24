package co.edu.javeriana.tg.integration.services.components;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.managed.Step;
import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;
import co.edu.javeriana.tg.entities.managed.StepParameterDefinition;
import co.edu.javeriana.tg.entities.managed.StepParameterDefinitionPK;
import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepParameterDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepRepository;
import co.edu.javeriana.tg.services.components.OperationService;
import co.edu.javeriana.tg.services.components.StepService;
import co.edu.javeriana.tg.services.components.WorkPlanService;

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
    private StepParameterDefinitionRepository stepParameterDefinitionRepository;

    @MockBean
    private WorkPlanService workPlanService;

    @MockBean
    private OperationService operationService;

    @MockBean
    private StepRepository stepRepository;

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

    @Test
    public void testEmptyStepsByWorkPlan(){
        assertEquals(0,stepService.stepsByWorkplan(1l).size());
    }

    @Test
    public void testNonEmptyStepsByWorkPlan(){
        Long workPlanNumber = 1l;
        Long stepNumber = 1l;
        when(stepDefinitionRepository.findByWorkPlan(workPlanNumber)).thenReturn(List.of(new StepDefinition(new StepDefinitionPK(workPlanNumber, stepNumber))));
        assertEquals(1, stepService.stepsByWorkplan(workPlanNumber).size());
    }

    @Test
    public void testEmptyFirstStepByWorkPlan(){
        assertNull(stepService.firstStepByWorkplan(1l));
    }

    @Test
    public void testNonEmptyFirstStepByWorkPlan(){
        Long workPlanNumber = 1l;
        Long stepNumber = 1l;
        when(stepDefinitionRepository.findFirstByWorkPlan(workPlanNumber)).thenReturn(new StepDefinition(new StepDefinitionPK(workPlanNumber, stepNumber)));
        when(workPlanService.getWorkplanById(workPlanNumber)).thenReturn(new WorkPlanDTO(new WorkPlanDefinition(workPlanNumber), ""));
        assertEquals(workPlanNumber, stepService.firstStepByWorkplan(workPlanNumber).getWorkPlan().getWorkPlanNumber());
    }

    @Test
    public void testEmptySaveStep(){
        Step s = new Step();
        when(stepRepository.save(s)).thenThrow(RuntimeException.class);
        assertDoesNotThrow(() -> stepService.saveStep(s));
    }

    @Test
    public void testNonEmptySaveStep(){
        Long workPlanNumber = 1l;
        Long stepNumber = 1l;
        Long orderNumber = 1l;
        Long orderPosition = 1l;
        Step step = new Step();
        step.setStepNumber(stepNumber);
        step.setWorkPlanNumber(workPlanNumber);
        step.setOrderNumber(orderNumber);
        step.setOrderPosition(orderPosition);
        StepParameterDefinition stepParameterDefinition = new StepParameterDefinition(new StepParameterDefinitionPK(workPlanNumber, stepNumber, orderNumber));
        stepParameterDefinition.setParameter("Par");
        stepParameterDefinition.setParameterNumber(orderNumber);
        
        when(stepParameterDefinitionRepository.findByRelatedWorkPlanAndStep(workPlanNumber, stepNumber)).thenReturn(List.of(stepParameterDefinition));
        assertDoesNotThrow(() -> stepService.saveStep(step));
        when(stepParameterDefinitionRepository.findByRelatedWorkPlanAndStep(workPlanNumber, stepNumber)).thenReturn(new ArrayList<>(0));
        assertDoesNotThrow(() -> stepService.saveStep(step));
    }
}
