package co.edu.javeriana.tg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceTests {
    @LocalServerPort
    private static int port;

    private static final TestRestTemplate rest=new TestRestTemplate();

    @BeforeAll
    static void startup(){
        System.out.println("Service Tests Started");
        System.out.println("App on port "+String.valueOf(port));
    }

    @Test
    static void test1(){
        assertTrue(rest!=null);
    }
    
}
