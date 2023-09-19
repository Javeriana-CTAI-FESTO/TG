package co.edu.javeriana.ctai.tgsecurity.service.external;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

/**
 * Servicio para procesar órdenes y realizar solicitudes HTTP a un servicio externo.
 */
@Service
public class OrderProcessingService {

    // Cola concurrente para almacenar órdenes a procesar
    private final ConcurrentLinkedQueue<Order> orderQueue = new ConcurrentLinkedQueue<>();

    // Cliente HTTP para realizar solicitudes HTTP
    private final RestTemplate restTemplate = new RestTemplate();

    // Número de orden actual
    private int orderNumber;

    // Repositorio de órdenes
    @Autowired
    private IOrderRepository orderRepository;

    /**
     * Inicializa el procesamiento de órdenes en un hilo separado al iniciar la aplicación.
     */
    @PostConstruct
    public void startProcessing() {
        // Crea un nuevo hilo para procesar órdenes en segundo plano
        Thread processingThread = new Thread(() -> {
            try {
                processOrders(); // Llama al método para procesar órdenes
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        processingThread.setDaemon(true); // Permite que el hilo se ejecute en segundo plano
        processingThread.start(); // Inicia el hilo
        System.out.println("Cola de órdenes inicializada");
    }


    /**
     * Obtiene el número de orden actual.
     *
     * @return El número de orden actual.
     */
    public int getOrderNumber() {
        return orderNumber; // Retorna el número de orden actual
    }

    /**
     * Establece el número de orden actual.
     *
     * @param orderNumber El número de orden a establecer.
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber; // Establece el número de orden
    }

    /**
     * Agrega una orden a la cola de procesamiento.
     *
     * @param order La orden a encolar.
     */
    public void enqueueOrder(Order order) {
        orderQueue.offer(order); // Agrega la orden a la cola
    }

    /**
     * Procesa las órdenes en la cola y realiza solicitudes HTTP a un servicio externo.
     *
     * @throws InterruptedException Si se interrumpe el hilo.
     */
    private void processOrders() throws InterruptedException {
        while (true) {
            try {
                if (!orderQueue.isEmpty()) {
                    Order order = orderQueue.poll(); // Obtiene una orden de la cola

                    if (sendHttpRequest(order)) {
                        // Si la solicitud HTTP fue exitosa, elimina la orden de la cola
                        System.out.println("Orden Enviada");
                    } else {
                        // Si la solicitud HTTP no fue exitosa, reintenta la solicitud vuelve a encolar la orden
                        System.out.println("Reintentando solicitud");
                        orderQueue.offer(order);
                    }
                } else {
                    // Si no hay órdenes en cola, espera un tiempo antes de revisar nuevamente
                    sleep(1000); // Espera 1 segundos antes de revisar nuevamente
                }
            } catch (InterruptedException e) {
                // Manejo de interrupción del hilo
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


}
