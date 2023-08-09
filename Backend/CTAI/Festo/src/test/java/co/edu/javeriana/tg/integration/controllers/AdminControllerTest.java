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

import co.edu.javeriana.tg.controllers.web.admin.AdminController;
import co.edu.javeriana.tg.services.MachineReportService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.PartService;
import co.edu.javeriana.tg.services.WorkPlanService;

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
    private PartService partService;
    
    @MockBean
    private WorkPlanService workPlanService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private MachineReportService reportService;

    private static final String BASEURI = "/api/admin";

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

    /*
     * @GetMapping("/reports/{resourceId}")
     * public ResponseEntity<List<ReportDTO>> getReportsForMachine(@PathVariable
     * Long resourceId) {
     * List<ReportDTO> reports = reportService.getForMachine(resourceId);
     * HttpStatus status = HttpStatus.OK;
     * if (reports.isEmpty())
     * status = HttpStatus.NO_CONTENT;
     * return new ResponseEntity<List<ReportDTO>>(reports, status);
     * }
        */
}
