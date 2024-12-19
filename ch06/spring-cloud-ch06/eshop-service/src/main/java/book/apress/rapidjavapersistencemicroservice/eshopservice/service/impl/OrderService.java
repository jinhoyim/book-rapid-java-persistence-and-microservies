package book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Order;
import book.apress.rapidjavapersistencemicroservice.eshopservice.repository.OrderRepository;

import brave.Tracing;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class OrderService {

    private final ExecutorService executorService;

    // @TimeLimiter는 CompletableFuture 반환하는 메서드에 사용할 수 있으므로 프로그래밍 코드로 처리한다.
    private final TimeLimiter timeLimiter = TimeLimiter.of(TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(3))
            .build());

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(
            ProductService productService,
            OrderRepository orderRepository,
            @LoadBalanced RestTemplate restTemplate,
            Tracing tracing
    ) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        executorService = tracing.currentTraceContext().executorService(
                Executors.newSingleThreadExecutor()
        );
    }

    @CircuitBreaker(name = "orderService", fallbackMethod = "handleInventoryFailure")
    public Order orderProduct() {
        try {
            return timeLimiter.executeFutureSupplier(() ->
                    CompletableFuture.supplyAsync(this::getOrder, executorService));
        } catch (Exception e) {
          throw new RuntimeException("Timeout occurred", e);
        }
    }

    private Order getOrder() {
        Order order = null;
        Map<String, Integer> map = null;
        ObjectMapper mapper = new ObjectMapper();

        // 호출이 차단되면 로그가 출력되지 않는다.
        log.info("call inventory service");
        String url = "http://INVENTORY-SERVICE/inventory/api/inventory/";
        String resultJson = restTemplate.getForObject(url + 1, String.class);
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

    private Order handleInventoryFailure(Throwable throwable) {
        log.error("Cannot connect to inventory service");
        return null;
    }
}
