package co.edu.javeriana.tg.integration.controllers;

import org.junit.runner.RunWith;
import org.junit.Test;
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

import co.edu.javeriana.tg.controllers.web.teacher.TeacherController;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.PartService;
import co.edu.javeriana.tg.services.WorkPlanService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ActiveProfiles("test")
@Profile("test")
@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private PartService partService;
    
    @MockBean
    private WorkPlanService workPlanService;

    @MockBean
    private OrderService orderService;

    private static final Gson gson = new Gson();
    private static final String BASEURI = "/api/teacher";

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
    public void testEmptyGetAllWorkPlanStatus() {
        try {
            when(orderService.getOrdersWithStatus()).thenReturn(new ArrayList<OrderDTO>(0));
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
            when(orderService.getOrdersWithStatus()).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetAllWorkPlanStatus() {
        try {
            when(orderService.getOrdersWithStatus()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllPlannedEnds() {
        try {
            when(orderService.getAllPlannedEnds()).thenReturn(new ArrayList<Date>(0));
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
            when(orderService.getAllPlannedEnds()).thenReturn(List.of(new Date()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetStatus() {
        try {
            when(orderService.getPossibleStatus()).thenReturn(new HashMap<Long, String>(0));
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
            when(orderService.getPossibleStatus()).thenReturn(Map.of(1l, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetStatus() {
        try {
            when(orderService.getPossibleStatus()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(orderService.filterByStatus(status)).thenReturn(new ArrayList<OrderDTO>(0));
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
            when(orderService.filterByStatus(status)).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status/"+String.valueOf(status))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(orderService.filterByStatus(status)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status/"+String.valueOf(status))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOrdersTime() {
        try {
            when(orderService.getOrdersWithTime()).thenReturn(new ArrayList<OrderDTO>(0));
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
            when(orderService.getOrdersWithTime()).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetOrdersTime() {
        try {
            when(orderService.getOrdersWithTime()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
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