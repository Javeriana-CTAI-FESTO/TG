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
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
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
    public void testEmptyGetAllProduceableParts() {
        try {
            when(studentService.getPartsThatCanBeProduced()).thenReturn(new ArrayList<>(0));
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
            when(studentService.getPartsThatCanBeProduced()).thenReturn(List.of(new PartDTO()));
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
            when(studentService.getPartsThatCanBeProduced()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/parts/production")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEmptyNewOrder() {
        try {
            Long partNumber = 1l, clientNumber = 1l, positions = 1l;
            when(studentService.generateNewOrder(partNumber, clientNumber, positions)).thenReturn(null);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/production/new-order")
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
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/production/new-order")
            .accept(MediaType.APPLICATION_JSON).param("partNumber", String.valueOf(partNumber)).param("clientNumber", String.valueOf(clientNumber)).param("positions", String.valueOf(positions))).andReturn().getResponse();
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testErrorNewOrder() {
        try {
            Long partNumber = 1l, clientNumber = 1l, positions = 1l;
            when(studentService.generateNewOrder(partNumber, clientNumber, positions)).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(post(BASEURI+"/parts/production/new-order")
            .accept(MediaType.APPLICATION_JSON).param("partNumber", String.valueOf(partNumber)).param("clientNumber", String.valueOf(clientNumber)).param("positions", String.valueOf(positions))).andReturn().getResponse();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
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
    public void testErrorGetAllOrdersStatus() {
        try {
            when(studentService.getOrdersWithStatus()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/")
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
            when(studentService.getWorkPlanById(product)).thenReturn(null);
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
            when(studentService.getWorkPlanById(product)).thenReturn(new WorkPlanWithStepsDTO());
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
            when(studentService.getWorkPlanById(product)).thenThrow(new RuntimeException());
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/work-plans/"+String.valueOf(product))
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
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
    public void testErrorGetAllOrdersPlannedEnds() {
        try {
            when(studentService.getAllOrdersPlannedEnds()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/ends")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
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
    public void testErrorGetOrdersStatus() {
        try {
            when(studentService.getOrdersPossibleStatus()).thenThrow(RuntimeException.class);
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
    public void testErrorGetOrdersByStatus() {
        try {
            Long status = 1l;
            when(studentService.filterOrdersByStatus(status)).thenThrow(RuntimeException.class);
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
    public void testErrorGetOrdersTime() {
        try {
            when(studentService.getOrdersWithTime()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/orders/time")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
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
    public void testErrorGetAllIndicators() {
        try {
            when(studentService.getProductionIndicators()).thenThrow(RuntimeException.class);
            MockHttpServletResponse response = mvc.perform(get(BASEURI+"/indicators/")
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();            
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        } catch (Exception e) {
            fail();
        }
    }
}
