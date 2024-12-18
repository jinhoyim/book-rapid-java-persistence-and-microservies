package book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl;

import book.apress.rapidjavapersistencemicroservice.eshopservice.http.InventoryClient;
import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Order;
import book.apress.rapidjavapersistencemicroservice.eshopservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OrderServiceWithFeign {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderServiceWithFeign(
            OrderRepository orderRepository,
            InventoryClient inventoryClient
    ) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order orderProduct() {
        Order order = null;

        Map<String, Integer> map = inventoryClient.getInventory(1L);
        log.info("Result from inventory service through feign: {}", map);
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
