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

import co.edu.javeriana.tg.controllers.web.StudentController;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.services.users.StudentService;

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
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private StudentService studentService;

    private static final String BASEURI = "/api/students";

    @Test
    public void testEmptyGetAllParts() {
        try {
            when(studentService.getAllParts()).thenReturn(new ArrayList<>(0));
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
            when(studentService.getAllParts()).thenReturn(List.of(new PartDTO()));
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
            when(studentService.getAllPartsTypes()).thenReturn(new HashMap<Long, String>(0));
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
            when(studentService.getAllPartsTypes()).thenReturn(Map.of(type, "Test"));
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
            when(studentService.getAllPartsTypes()).thenThrow(new RuntimeException());
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
            when(studentService.getAllPartsByType(type)).thenReturn(new ArrayList<PartDTO>(0));
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
            when(studentService.getAllPartsByType(type)).thenReturn(List.of(new PartDTO()));
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
            when(studentService.getAllPartsByType(type)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/type/"+String.valueOf(type))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyNewOrder() {
        try {
            Long partNumber = 1l, clientNumber = 1l, positions = 1l;
            when(studentService.generateNewOrder(partNumber, clientNumber, positions)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/new-order")
            .accept(MediaType.APPLICATION_JSON).param("partNumber", String.valueOf(partNumber)).param("clientNumber", String.valueOf(clientNumber)).param("positions", String.valueOf(positions))).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyNewOrder() {
        try {
            Long partNumber = 1l, clientNumber = 1l, positions = 1l;
            when(studentService.generateNewOrder(partNumber, clientNumber, positions)).thenReturn(new OrderDTO());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/new-order")
            .accept(MediaType.APPLICATION_JSON).param("partNumber", String.valueOf(partNumber)).param("clientNumber", String.valueOf(clientNumber)).param("positions", String.valueOf(positions))).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundNewOrder() {
        try {
           Long partNumber = 1l, clientNumber = 1l, positions = 1l;
            when(studentService.generateNewOrder(partNumber, clientNumber, positions)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/new-order")
            .accept(MediaType.APPLICATION_JSON).param("partNumber", String.valueOf(partNumber)).param("clientNumber", String.valueOf(clientNumber)).param("positions", String.valueOf(positions))).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllOrdersStatus() {
        try {
            when(studentService.getOrdersWithStatus()).thenReturn(new ArrayList<OrderDTO>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllOrdersStatus() {
        try {
            when(studentService.getOrdersWithStatus()).thenReturn(List.of(new OrderDTO()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetAllOrdersStatus() {
        try {
            when(studentService.getOrdersWithStatus()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetAllOrdersPlannedEnds() {
        try {
            when(studentService.getAllOrdersPlannedEnds()).thenReturn(new ArrayList<Date>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetAllOrdersPlannedEnds() {
        try {
            when(studentService.getAllOrdersPlannedEnds()).thenReturn(List.of(new Date()));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyGetOrdersStatus() {
        try {
            when(studentService.getOrdersPossibleStatus()).thenReturn(new HashMap<Long, String>(0));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNonEmptyGetOrdersStatus() {
        try {
            when(studentService.getOrdersPossibleStatus()).thenReturn(Map.of(1l, "Test"));
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/status")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotFoundGetOrdersStatus() {
        try {
            when(studentService.getOrdersPossibleStatus()).thenThrow(new RuntimeException());
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
            when(studentService.filterOrdersByStatus(status)).thenReturn(new ArrayList<OrderDTO>(0));
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
            when(studentService.filterOrdersByStatus(status)).thenReturn(List.of(new OrderDTO()));
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
            when(studentService.filterOrdersByStatus(status)).thenThrow(new RuntimeException());
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
            when(studentService.getOrdersWithTime()).thenReturn(new ArrayList<OrderDTO>(0));
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
            when(studentService.getOrdersWithTime()).thenReturn(List.of(new OrderDTO()));
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
            when(studentService.getOrdersWithTime()).thenThrow(new RuntimeException());
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
            when(studentService.getProductionIndicators()).thenReturn(new ArrayList<IndicatorAux>(0));
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
            when(studentService.getProductionIndicators()).thenReturn(List.of(new IndicatorAux()));
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
            when(studentService.getProductionIndicators()).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
    /*
    @Test
    public void testEmptyGetAllWorkPlans() {
        try {
            when(studentService.getAllWorkPlans()).thenReturn(new ArrayList<>(0));
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

    
    */
}
