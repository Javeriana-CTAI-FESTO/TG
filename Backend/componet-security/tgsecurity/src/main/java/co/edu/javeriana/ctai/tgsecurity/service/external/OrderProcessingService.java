package co.edu.javeriana.ctai.tgsecurity.service.external;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.hql.internal.ast.tree.TableReferenceNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

@Service
public class OrderProcessingService {

    private final ConcurrentLinkedQueue<Order> orderQueue = new ConcurrentLinkedQueue<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private int orderNumber;

    @Autowired
    private IOrderRepository orderRepository;

    @PostConstruct
    public void startProcessing() {
        Thread processingThread = new Thread(() -> {
            try {
                processOrders();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        processingThread.setDaemon(true); // Permite que el hilo se ejecute en segundo plano
        processingThread.start();
        System.out.println("Cola de órdenes inicializada");
    }

    private void processOrders() throws InterruptedException {

        while (true) {
            try {
                if (!orderQueue.isEmpty()) {
                    Order order = orderQueue.poll();

                    if(sendHttpRequest(order)) {
                        // Si la solicitud fue exitosa, se elimina la orden de la cola
                        System.out.println("Orden procesada");
                    } else {
                        // Si la solicitud no fue exitosa, reintenta la solicitud
                        System.out.println("Reintentando solicitud");
                        orderQueue.offer(order);
                    }


                } else {
                    // Si no hay órdenes en cola, espera un tiempo antes de revisar nuevamente
                    sleep(3000); // Espera 5 segundos antes de revisar nuevamente
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            } catch (Exception ex) {
                // Reintenta la solicitud si ocurre un error
                System.out.println("Reintentando solicitud ...");
            }
        }
    }

    private boolean sendHttpRequest(Order order) {
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

            String responseBody = responseEntity.getBody();

            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            int orderNumber = jsonObject.get("orderNumber").getAsInt();
            order.setOrderNumber(orderNumber);
            this.orderNumber = orderNumber;

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // La solicitud fue exitosa
                orderRepository.save(order);
                System.out.println("Orden guardada en BD: id-" + orderNumber);

                return true;
            } else {
                // La solicitud no fue exitosa
                return false;
            }
        } catch (Exception e) {
            // Manejo de excepciones al realizar la solicitud HTTP
            return false;
        }
    }

    public void enqueueOrder(Order order) {
        orderQueue.offer(order);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
