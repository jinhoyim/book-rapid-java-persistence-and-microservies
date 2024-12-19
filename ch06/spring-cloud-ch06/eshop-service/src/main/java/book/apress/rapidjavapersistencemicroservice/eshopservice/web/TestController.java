package book.apress.rapidjavapersistencemicroservice.eshopservice.web;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Order;
import book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@Slf4j
public class TestController {

    private final OrderService orderService;
    private final OrderServiceWithFeign orderServiceWithFeign;
    private final OrderServiceWithRestClient orderServiceWithRestClient;
    private final ProductService productService;
    private final CustomerService customerService;

    public TestController(
            OrderService orderService,
            ProductService productService,
            CustomerService customerService,
            OrderServiceWithFeign orderServiceWithFeign,
            OrderServiceWithRestClient orderServiceWithRestClient
    ) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
        this.orderServiceWithFeign = orderServiceWithFeign;
        this.orderServiceWithRestClient = orderServiceWithRestClient;
    }

    @PostMapping(value = "/api/orders", produces = "application/json")
    public ResponseEntity<?> purchaseSampleProduct() {
        customerService.registerNewCustomers();
        productService.registerNewProducts();

        Order order = orderService.orderProduct();
        log.info("Order status: {}", Objects.isNull(order));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrderId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/api/orders-with-feign", produces = "application/json")
    public ResponseEntity<?> purchaseSampleProductWithFeign() {
        customerService.registerNewCustomers();
        productService.registerNewProducts();

        Order order = orderServiceWithFeign.orderProduct();
        log.info("Order status: {}", Objects.isNull(order));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrderId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/api/orders-with-rest-client", produces = "application/json")
    public ResponseEntity<?> purchaseSampleProductWithRestClient() {
        customerService.registerNewCustomers();
        productService.registerNewProducts();

        Order order = orderServiceWithRestClient.orderProduct();
        log.info("Order status: {}", Objects.isNull(order));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrderId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
