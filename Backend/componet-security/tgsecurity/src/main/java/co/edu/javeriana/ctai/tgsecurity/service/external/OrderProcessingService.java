package co.edu.javeriana.ctai.tgsecurity.service.external;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class OrderProcessingService {

    private final Queue<Order> orderQueue = new LinkedList<>();
    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public String processOrders() {
        String body = null;

        while (true) {
            if (!orderQueue.isEmpty()) {
                Order order = orderQueue.poll();

                try {
                    // URL para realizar la solicitud POST
                    String url = "http://localhost:8080/api/students/parts/production/new-order?partNumber="
                            + order.getId_part()
                            + "&clientNumber=0&positions=1";

                    // Realiza la solicitud POST con el URL construido
                    ResponseEntity<String> responseEntity = restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            null, // No se envía un cuerpo en este caso
                            String.class
                    );

                    System.out.println("Procesando orden en cola");
                    String response = responseEntity.getStatusCode().toString();
                    System.out.println("Respuesta de FESTO module: " + response);
                    body = responseEntity.getBody();

                } catch (Exception e) {
                    // Manejo de errores
                    System.out.println("Error de conexión con el módulo FESTO");
                    System.out.println("Re-encolar orden");
                    // Volver a encolar la orden
                    orderQueue.offer(order);
                }
                return body;
            } else {
                // Si no hay órdenes en cola, espera un tiempo antes de revisar nuevamente
                try {
                    Thread.sleep(5000); // Espera 5 segundos antes de revisar nuevamente
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void enqueueOrder(Order order) {
        orderQueue.offer(order);
    }
}
