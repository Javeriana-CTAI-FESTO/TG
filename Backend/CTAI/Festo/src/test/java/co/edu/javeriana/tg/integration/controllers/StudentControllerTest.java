package co.edu.javeriana.tg.integration.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;
import co.edu.javeriana.tg.services.ClientService;
import co.edu.javeriana.tg.services.OperationService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.ResourceService;
import co.edu.javeriana.tg.services.StepService;
import co.edu.javeriana.tg.services.WorkPlanService;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
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
