package co.edu.javeriana.tg.integration.controllers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import co.edu.javeriana.tg.controllers.web.AdminController;
import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.PartsConsumedByOrderDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.users.AdminService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureJsonTesters
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private AdminService adminService;

    private static final String BASEURI = "/api/admin";

    private static final Gson gson = new Gson();

    @Test
    public void testEmptyGetAllFailsReports() {
        try {
            when(adminService.getAllFails()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllFailsReports() {
        try {
            when(adminService.getAllFails()).thenReturn(List.of(new MachineReportDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllFailsReports() {
        try {
            when(adminService.getAllFails()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetFailReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getAllFailsForMachine(machine)).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetFailReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getAllFailsForMachine(machine)).thenReturn(List.of(new ReportDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetFailReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getAllFailsForMachine(machine)).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/fails/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllReports() {
        try {
            when(adminService.getAllReports()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllReports() {
        try {
            when(adminService.getAllReports()).thenReturn(List.of(new MachineReportDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllReports() {
        try {
            when(adminService.getAllReports()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getReportsForMachine(machine)).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getReportsForMachine(machine)).thenReturn(List.of(new ReportDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetReportsForMachine() {
        try {
            Long machine = 1l;
            when(adminService.getReportsForMachine(machine)).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

     @Test
    public void testEmptyGetAllPartsAvailable() {
        try {
            when(adminService.getAllPartsAvailable()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/available")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPartsAvailable() {
        try {
            when(adminService.getAllPartsAvailable()).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/available")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllPartsAvailable() {
        try {
            when(adminService.getAllPartsAvailable()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/available")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPartsUnvailable() {
        try {
            when(adminService.getAllPartsUnavailable()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/unavailable")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPartsUnavailable() {
        try {
            when(adminService.getAllPartsUnavailable()).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/unavailable")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllPartsUnavailable() {
        try {
            when(adminService.getAllPartsUnavailable()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/unavailable")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllParts() {
        try {
            when(adminService.getAllParts()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllParts() {
        try {
            when(adminService.getAllParts()).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllParts() {
        try {
            when(adminService.getAllParts()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllWorkPlans() {
        try {
            when(adminService.getAllWorkPlans()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllWorkPlans() {
        try {
            when(adminService.getAllWorkPlans()).thenReturn(List.of(new WorkPlanDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllWorkPlans() {
        try {
            when(adminService.getAllWorkPlans()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlansById() {
        try {
            Long product = 1l;
            when(adminService.getWorkPlanById(product)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetWorkPlansById() {
        try {
            Long product = 1l;
            when(adminService.getWorkPlanById(product)).thenReturn(new WorkPlanWithStepsDTO());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetWorkPlansById() {
        try {
            Long product = 1l;
            when(adminService.getWorkPlanById(product)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlanTypes() {
        try {
            when(adminService.getAllWorkPlansTypes()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetWorkPlanTypes() {
        try {
            when(adminService.getAllWorkPlansTypes()).thenReturn(Map.of(1l, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetWorkPlanTypes() {
        try {
            when(adminService.getAllWorkPlansTypes()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlansByType() {
        try {
            Long type = 1l;
            when(adminService.getWorkPlansByType(type)).thenReturn(new ArrayList<WorkPlanDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetWorkPlansByType() {
        try {
            Long type = 1l;
            when(adminService.getWorkPlansByType(type)).thenReturn(List.of(new WorkPlanDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetWorkPlansByType() {
        try {
            Long type = 1l;
            when(adminService.getWorkPlansByType(type)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testEmptyGetAllPartsType() {
        try {
            when(adminService.getAllPartsTypes()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOperations() {
        try {
            when(adminService.getAllOperations()).thenReturn(new ArrayList<OperationDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetOperations() {
        try {
            when(adminService.getAllOperations()).thenReturn(List.of(new OperationDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetOperations() {
        try {
            when(adminService.getAllOperations()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOperationsByResource() {
        try {
            long resource = 1l;
            when(adminService.getOperationsForResource(resource)).thenReturn(new ArrayList<ResourceForOperationDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations/"+String.valueOf(resource))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetOperationsForResource() {
        try {
            long resource = 1l;
            when(adminService.getOperationsForResource(resource)).thenReturn(List.of(new ResourceForOperationDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations/"+String.valueOf(resource))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetOperationsForResource() {
        try {
            long resource = 1l;
            when(adminService.getOperationsForResource(resource)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/operations/"+String.valueOf(resource))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyCreateWorkPlan() {
        try {
            CreateWorkPlanAux aux = new CreateWorkPlanAux();
            when(adminService.createWorkPlan(aux)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/work-plans")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyCreateWorkPlan() {
        try {
            CreateWorkPlanAux aux = new CreateWorkPlanAux();
            when(adminService.createWorkPlan(aux)).thenReturn(new WorkPlanDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/work-plans")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorCreateWorkPlan() {
        try {
            CreateWorkPlanAux aux = new CreateWorkPlanAux();
            when(adminService.createWorkPlan(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/work-plans")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyCreatePart() {
        try {
            CreatePartAux aux = new CreatePartAux();
            when(adminService.createPart(aux)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyCreatePart() {
        try {
            CreatePartAux aux = new CreatePartAux();
            when(adminService.createPart(aux)).thenReturn(new PartDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorCreatPart() {
        try {
            CreatePartAux aux = new CreatePartAux();
            when(adminService.createPart(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllWorkPlanStatus() {
        try {
            when(adminService.getOrdersWithStatus()).thenReturn(new ArrayList<OrderDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllWorkPlanStatus() {
        try {
            when(adminService.getOrdersWithStatus()).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllWorkPlanStatus() {
        try {
            when(adminService.getOrdersWithStatus()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPlannedEnds() {
        try {
            when(adminService.getAllOrdersPlannedEnds()).thenReturn(new ArrayList<Date>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPlannedEnds() {
        try {
            when(adminService.getAllOrdersPlannedEnds()).thenReturn(List.of(new Date()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllPlannedEnds() {
        try {
            when(adminService.getAllOrdersPlannedEnds()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllClients() {
        try {
            when(adminService.getAllClients()).thenReturn(new ArrayList<ClientDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllClients() {
        try {
            when(adminService.getAllClients()).thenReturn(List.of(new ClientDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllClients() {
        try {
            when(adminService.getAllClients()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetStatus() {
        try {
            when(adminService.getOrdersPossibleStatus()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetStatus() {
        try {
            when(adminService.getOrdersPossibleStatus()).thenReturn(Map.of(1l, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetStatus() {
        try {
            when(adminService.getOrdersPossibleStatus()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(adminService.filterOrdersByStatus(status)).thenReturn(new ArrayList<OrderDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status/"+String.valueOf(status))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(adminService.filterOrdersByStatus(status)).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status/"+String.valueOf(status))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(adminService.filterOrdersByStatus(status)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status/"+String.valueOf(status))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOrdersTime() {
        try {
            when(adminService.getOrdersWithTime()).thenReturn(new ArrayList<OrderDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetOrdersTime() {
        try {
            when(adminService.getOrdersWithTime()).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetEnableOrders() {
        try {
            Long orderNumber = 1l;
            when(adminService.enableOrder(orderNumber)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(put(BASEURI+"/orders/"+String.valueOf(orderNumber))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetSteps() {
        try {
            when(adminService.getAllStepsDefined()).thenReturn(new ArrayList<StepDefinitionDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/steps")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetSteps() {
        try {
            when(adminService.getAllStepsDefined()).thenReturn(List.of(new StepDefinitionDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/steps")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetSteps() {
        try {
            when(adminService.getAllStepsDefined()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/steps")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetEnableOrders() {
        try {
            Long orderNumber = 1l;
            when(adminService.enableOrder(orderNumber)).thenReturn(new OrderDTO());
            MockHttpServletResponse response = mvc.perform(put(BASEURI+"/orders/"+String.valueOf(orderNumber))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorEnableOrders() {
        try {
            Long orderNumber = 1l;
            when(adminService.enableOrder(orderNumber)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(put(BASEURI+"/orders/"+String.valueOf(orderNumber))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetOrdersTime() {
        try {
            when(adminService.getOrdersWithTime()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetPartsConsumedByWorkPlan() {
        try {
            when(adminService.getAllPartsConsumedByOrders()).thenReturn(new ArrayList<PartsConsumedByOrderDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetPartsConsumedByWorkPlan() {
        try {
            when(adminService.getAllPartsConsumedByOrders()).thenReturn(List.of(new PartsConsumedByOrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetPartsConsumedByWorkPlan() {
        try {
            when(adminService.getAllPartsConsumedByOrders()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPartsType() {
        try {
            Long type = 1l;
            when(adminService.getAllPartsTypes()).thenReturn(Map.of(type, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllPartsType() {
        try {
            when(adminService.getAllPartsTypes()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPartsByType() {
        try {
            Long type = 1l;
            when(adminService.getAllPartsByType(type)).thenReturn(new ArrayList<PartDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPartsByType() {
        try {
            Long type = 1l;
            when(adminService.getAllPartsByType(type)).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllPartsByType() {
        try {
            Long type = 1l;
            when(adminService.getAllPartsByType(type)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testEmptyGetAllProduceableParts() {
        try {
            when(adminService.getPartsThatCanBeProduced()).thenReturn(new ArrayList<PartDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/production")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllProduceableParts() {
        try {
            when(adminService.getPartsThatCanBeProduced()).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/production")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllProduceableParts() {
        try {
            when(adminService.getPartsThatCanBeProduced()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/production")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetResources() {
        try {
            when(adminService.getAllResources()).thenReturn(new ArrayList<ResourceDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/resources")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetResources() {
        try {
            when(adminService.getAllResources()).thenReturn(List.of(new ResourceDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/resources")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetResources() {
        try {
            when(adminService.getAllResources()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/resources")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllIndicators() {
        try {
            when(adminService.getProductionIndicators()).thenReturn(new ArrayList<IndicatorAux>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllIndicators() {
        try {
            when(adminService.getProductionIndicators()).thenReturn(List.of(new IndicatorAux()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorGetAllIndicators() {
        try {
            when(adminService.getProductionIndicators()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyCreateClient() {
        try {
            Client aux = new Client();
            when(adminService.createClient(aux)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyCreateClient() {
        try {
            Client aux = new Client();
            when(adminService.createClient(aux)).thenReturn(new ClientDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorCreateClient() {
        try {
           Client aux = new Client();
            when(adminService.createClient(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
}
