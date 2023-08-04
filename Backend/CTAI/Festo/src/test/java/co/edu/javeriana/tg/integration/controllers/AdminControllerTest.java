package co.edu.javeriana.tg.integration.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.PartRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;
import co.edu.javeriana.tg.services.ClientService;
import co.edu.javeriana.tg.services.MachineReportService;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.PartService;
import co.edu.javeriana.tg.services.ResourceService;
import co.edu.javeriana.tg.services.StepService;
import co.edu.javeriana.tg.services.WorkPlanService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {

    /*@Test
    public void main(){
        assertTrue(true);
    }
    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

     * @Autowired
     * private PartService partService;
     * 
     * @Autowired
     * private WorkPlanService workPlanService;
     * 
     * @Autowired
     * private OrderService orderService;
     * 
     * @Autowired
     * private MachineReportService machineReportService;
     * 
     * @Autowired
     * private OperationService operationService;
     * 
     * @Autowired
     * private StepService stepService;
     * 
     * @Autowired
     * private ClientService clientService;
     * 
     * @Autowired
     * private ResourceService resourceService;
     * 
     * @Autowired
     * private PartRepository partRepository;
     * 
     * @Autowired
     * private WorkPlanDefinitionRepository workPlanRepository;
     * 
     * @Autowired
     * private WorkPlanTypeRepository workPlanTypeRepository;
     * 
     * @Autowired
     * private StepDefinitionRepository stepDefinitionRepository;
     * 
     * @Autowired
     * private OrderRepository orderRepository;
     * 
     * @Autowired
     * private OrderPositionRepository orderPositionRepository;
     * 
     * @Autowired
     * private ResourceForOperationRepository resourceForOperationRepository;
     * 
     * @Autowired
     * private MachineReportRepository reportRepository;
    

    private static HttpHeaders headers;
    
    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/admin" + uri;
    }

    // View machines status
    @Test
    public void testGetAllReports() {
        HttpEntity<List<MachineReportDTO>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<MachineReportDTO>> reportsResponse = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<MachineReportDTO>>() {
                });
        List<MachineReportDTO> reports = reportsResponse.getBody();
        /*
         * Long initialReports = reportRepository.count();
         * if (initialReports == 0){
         * assertTrue(reports.isEmpty());
         * assertEquals(HttpStatus.NO_CONTENT, reportsResponse.getStatusCode());
         * } else{
         * assertEquals(HttpStatus.OK, reportsResponse.getStatusCode());
         * }
         
    } */

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
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WorkPlanService workPlanService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OperationService operationService;

    @Autowired
    private StepService stepService;
    
    @Autowired
    private ClientService clientService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private WorkPlanDefinitionRepository workPlanRepository;

    @Autowired
    private WorkPlanTypeRepository workPlanTypeRepository;

    @Autowired
    private StepDefinitionRepository stepDefinitionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    @Autowired
    private ResourceForOperationRepository resourceForOperationRepository;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
