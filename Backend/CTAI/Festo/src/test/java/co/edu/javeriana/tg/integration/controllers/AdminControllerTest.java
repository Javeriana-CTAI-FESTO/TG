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

import co.edu.javeriana.tg.controllers.web.admin.AdminController;
import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.MachineReportService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.PartService;
import co.edu.javeriana.tg.services.WorkPlanService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.ArrayList;
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
    private PartService partService;
    
    @MockBean
    private WorkPlanService workPlanService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private MachineReportService reportService;

    private static final String BASEURI = "/api/admin";

    private static final Gson gson = new Gson();

    @Test
    public void testEmptyGetAllReports() {
        try {
            when(reportService.getAll()).thenReturn(new ArrayList<>(0));
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
            when(reportService.getAll()).thenReturn(List.of(new MachineReportDTO()));
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
            when(reportService.getForMachine(machine)).thenReturn(new ArrayList<>(0));
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
            when(reportService.getForMachine(machine)).thenReturn(List.of(new ReportDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/reports/"+String.valueOf(machine))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllWorkPlans() {
        try {
            when(workPlanService.getAll()).thenReturn(new ArrayList<>(0));
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
            when(workPlanService.getAll()).thenReturn(List.of(new WorkPlanDTO()));
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

    @Test
    public void testEmptyGetAllIndicators() {
        try {
            when(orderService.getIndicators()).thenReturn(new ArrayList<IndicatorAux>(0));
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
            when(orderService.getIndicators()).thenReturn(List.of(new IndicatorAux()));
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
            when(orderService.getIndicators()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
}
