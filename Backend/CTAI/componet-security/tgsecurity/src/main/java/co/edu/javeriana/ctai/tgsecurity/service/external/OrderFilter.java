package co.edu.javeriana.ctai.tgsecurity.service.external;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.google.gson.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OrderFilter {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final RestTemplate restTemplate = new RestTemplate();

    public OrderFilter() {
        // Inicia la programación de tareas con un retraso inicial de 0 segundos y una ejecución periódica de 5 segundos
        //executorService.scheduleAtFixedRate(this::getMESOrders, 0, 5, TimeUnit.SECONDS);
    }

    public List<Long> getMESOrders() {

        return sendHttpRequest();
    }

    private List<Long> sendHttpRequest() {

        try {
            String url = "http://localhost:8080/api/admin/orders";
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            String responseBody = responseEntity.getBody();

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // Analiza el JSON en una matriz de elementos JSON
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(responseBody).getAsJsonArray();

                // Inicializa una lista para almacenar los números de orden
                List<Long> orderNumbers = new ArrayList<>();
                

                // Itera a través de cada objeto JSON en la matriz
                Iterator<JsonElement> iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JsonElement jsonElement = iterator.next();
                    long orderNumber = jsonElement.getAsJsonObject().get("orderNumber").getAsLong();
                    orderNumbers.add(orderNumber);
                }


                // Ahora 'orderNumbers' contiene la lista de números de orden
                System.out.println("Números de Orden: " + orderNumbers);

                // Devuelve la lista de números de orden
                return orderNumbers;

            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
