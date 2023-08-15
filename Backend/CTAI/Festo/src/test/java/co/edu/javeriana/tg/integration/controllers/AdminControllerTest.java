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
import co.edu.javeriana.tg.controllers.web.AdminController;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.users.AdminService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.ArrayList;

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


    /*
    @Test
    public void testEmptyGetAllWorkPlans() {
        try {
            when(adminService.getAllWorkPlans()).thenReturn(new ArrayList<>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/")
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
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetAllWorkPlans() {
        try {
            when(workPlanService.getAll()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlansById() {
        try {
            Long product = 1l;
            when(workPlanService.getById(product)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/"+String.valueOf(product))
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
            when(workPlanService.getById(product)).thenReturn(new WorkPlanDTO());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetWorkPlansById() {
        try {
            Long product = 1l;
            when(workPlanService.getById(product)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlanTypes() {
        try {
            when(workPlanService.getTypes()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetWorkPlanTypes() {
        try {
            when(workPlanService.getTypes()).thenReturn(Map.of(1l, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetWorkPlanTypes() {
        try {
            when(workPlanService.getTypes()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetWorkPlansByType() {
        try {
            Long type = 1l;
            when(workPlanService.getWorkPlansByType(type)).thenReturn(new ArrayList<WorkPlanDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type/"+String.valueOf(type))
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
            when(workPlanService.getWorkPlansByType(type)).thenReturn(List.of(new WorkPlanDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetWorkPlansByType() {
        try {
            Long type = 1l;
            when(workPlanService.getWorkPlansByType(type)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/products/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyCreateWorkPlan() {
        try {
            CreateWorkPlanAux aux = new CreateWorkPlanAux();
            when(workPlanService.createWorkPlan(aux)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/products")
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
            when(workPlanService.createWorkPlan(aux)).thenReturn(new WorkPlanDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/products")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundCreateWorkPlan() {
        try {
           CreateWorkPlanAux aux = new CreateWorkPlanAux();
            when(workPlanService.createWorkPlan(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/products")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllParts() {
        try {
            when(partService.getAll()).thenReturn(new ArrayList<>(0));
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
            when(partService.getAll()).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPartsType() {
        try {
            when(partService.getAllTypes()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllPartsType() {
        try {
            Long type = 1l;
            when(partService.getAllTypes()).thenReturn(Map.of(type, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetAllPartsType() {
        try {
            when(partService.getAllTypes()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPartsByType() {
        try {
            Long type = 1l;
            when(partService.getAllByType(type)).thenReturn(new ArrayList<PartDTO>(0));
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
            when(partService.getAllByType(type)).thenReturn(List.of(new PartDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetAllPartsByType() {
        try {
            Long type = 1l;
            when(partService.getAllByType(type)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyCreatePart() {
        try {
            CreatePartAux aux = new CreatePartAux();
            when(partService.createPart(aux)).thenReturn(null);
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
            when(partService.createPart(aux)).thenReturn(new PartDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();           
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundCreatePart() {
        try {
           CreatePartAux aux = new CreatePartAux();
            when(partService.createPart(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
    */

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
    public void testNotFoundGetAllIndicators() {
        try {
            when(adminService.getProductionIndicators()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
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
    public void testNotFoundCreateClient() {
        try {
           Client aux = new Client();
            when(adminService.createClient(aux)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/clients")
            .accept(MediaType.APPLICATION_JSON).content(gson.toJson(aux)).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
}
