package co.edu.javeriana.tg.integration.services;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.managed.Operation;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.ResourceForOperationService;
import co.edu.javeriana.tg.services.ResourceService;
import co.edu.javeriana.tg.services.StepService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class ResourceForOperationServiceTest {
    @Autowired
    private ResourceForOperationService resourceForOperationService;

    @MockBean
    private ResourceForOperationRepository resourceForOperationRepository;

    @MockBean
    private StepService stepService;

    @MockBean
    private OperationService operationService;

    @MockBean
    private ResourceService resourceService;

    @Test
    public void testEmptyConvertToDTO(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        ResourceForOperationPK pk = new ResourceForOperationPK(resoutrceID, operationID);
        List<ResourceForOperation> l = List.of(new ResourceForOperation(pk));
        when(resourceService.getById(resoutrceID)).thenThrow(RuntimeException.class);
        assertNull(resourceForOperationService.convertToDTO(l));
    }

    @Test
    public void testNonEmptyConvertToDTO(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        when(operationService.get(operationID)).thenReturn(new OperationDTO(new Operation(operationID)));
        when(resourceService.getById(resoutrceID)).thenReturn(new ResourceDTO(new Resource(resoutrceID)));
        ResourceForOperationPK pk = new ResourceForOperationPK(resoutrceID, operationID);
        List<ResourceForOperation> l = List.of(new ResourceForOperation(pk));
        assertEquals(l.get(0).getResource(), resourceForOperationService.convertToDTO(l).getResources().get(0).getId());
    }

    @Test
    public void testEmptyGetResourcesGivenOperation(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        when(resourceForOperationRepository.findByOperation(operationID)).thenReturn(List.of(new ResourceForOperation(new ResourceForOperationPK(resoutrceID, operationID))));
        when(resourceService.getById(resoutrceID)).thenThrow(RuntimeException.class);
        assertNull(resourceForOperationService.getResourcesGivenOperation(operationID));
    }

    @Test
    public void testNonEmptyGetResourcesGivenOperation(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        when(resourceForOperationRepository.findByOperation(operationID)).thenReturn(List.of(new ResourceForOperation(new ResourceForOperationPK(resoutrceID, operationID))));
        when(operationService.get(operationID)).thenReturn(new OperationDTO(new Operation(operationID)));
        when(resourceService.getById(resoutrceID)).thenReturn(new ResourceDTO(new Resource(resoutrceID)));
        assertEquals(resoutrceID, resourceForOperationService.getResourcesGivenOperation(operationID).getResources().get(0).getId());
    }

    @Test
    public void testEmptyGetOperationsGivenResource(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        when(resourceForOperationRepository.findByResource(resoutrceID)).thenReturn(List.of(new ResourceForOperation(new ResourceForOperationPK(resoutrceID, operationID))));
        assertEquals(0,resourceForOperationService.getOperationsGivenResource(resoutrceID).size());
    }

    @Test
    public void testNonEmptyGetOperationsGivenResource(){
        Long operationID = 1l;
        Long resoutrceID = 1L;
        when(operationService.get(operationID)).thenReturn(new OperationDTO(new Operation(operationID)));
        when(resourceService.getById(resoutrceID)).thenReturn(new ResourceDTO(new Resource(resoutrceID)));
        when(resourceForOperationRepository.findByResource(resoutrceID)).thenReturn(List.of(new ResourceForOperation(new ResourceForOperationPK(resoutrceID, operationID)), new ResourceForOperation(new ResourceForOperationPK(resoutrceID+1, operationID+1))));
        assertEquals(1,resourceForOperationService.getOperationsGivenResource(resoutrceID).size());
    }        

    @Test
    public void testEmptyTimeForWorkplan(){
        Long workPlanTime = 1l;
        Long operationID = 1l;
        when(stepService.getWorkPlanTime(workPlanTime)).thenReturn(new WorkPlanTimeAux(4l, List.of(operationID)));
        when(resourceForOperationRepository.timeTakenByOperation(operationID)).thenReturn(2l);
        assertTrue(6l == resourceForOperationService.timeForWorkPlan(workPlanTime));
    }

    @Test
    public void testNonEmptyTimeForWorkplan(){
        Long workPlanTime = 1l;
        Long operationID = 1l;
        when(stepService.getWorkPlanTime(workPlanTime)).thenReturn(new WorkPlanTimeAux(0l, List.of(operationID)));
        when(resourceForOperationRepository.timeTakenByOperation(operationID)).thenReturn(0l);
        assertTrue(0l == resourceForOperationService.timeForWorkPlan(workPlanTime));
    }        
}
