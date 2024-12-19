package book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Order;
import book.apress.rapidjavapersistencemicroservice.eshopservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceWithRestClient {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final RestClient.Builder restClientBuilder;

    public OrderServiceWithRestClient(
            ProductService productService,
            OrderRepository orderRepository,
            @LoadBalanced RestClient.Builder restClientBuilder
    ) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.restClientBuilder = restClientBuilder;
    }

    public Order orderProduct() {
        Order order = null;
        Map<String, Integer> map = null;
        ObjectMapper mapper = new ObjectMapper();

        String url = "http://INVENTORY-SERVICE/inventory/api/inventory/";
        String resultJson = restClientBuilder
                .build()
                .get()
                .uri(url + 1)
                .retrieve()
                .body(String.class);

        try {
            map = mapper.readValue(resultJson.getBytes(), HashMap.class);
        } catch (Exception e) {
            log.error("Error while reading back json value: {}", resultJson);
            return null;
        }

        Integer qty = map.get("quantity");
        if (qty >= 2)
            order = createOrder(1L, 1L, 2, 400);

        log.info("Orders {}", orderRepository.findAll());

        return order;
    }

    public Order createOrder(Long productId, Long customerId, int quantity, int price) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setPrice(price);
        order.setQuantity(quantity);
        order = orderRepository.save(order);
        return order;
    }
}
