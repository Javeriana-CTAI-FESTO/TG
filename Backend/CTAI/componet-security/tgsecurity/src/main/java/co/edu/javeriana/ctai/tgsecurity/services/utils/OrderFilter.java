package co.edu.javeriana.ctai.tgsecurity.services.utils;

import co.edu.javeriana.ctai.tgsecurity.services.cp_facrory.impl.payloads.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OrderFilter {

    private static final Logger LOGGER = Logger.getLogger(OrderFilter.class.getName());

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
            ResponseEntity<OrderResponse[]> responseEntity = restTemplate.getForEntity(url, OrderResponse[].class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                OrderResponse[] orderResponses = responseEntity.getBody();

                if (orderResponses != null) {
                    return Arrays.stream(orderResponses)
                            .map(OrderResponse::getOrderNumber)
                            .collect(Collectors.toList());
                }
            }
        } catch (RestClientException e) {
            LOGGER.warning("Error al realizar la solicitud HTTP a CP_FACTORY: " + e.getMessage());
        }

        return Collections.emptyList();
    }

}
